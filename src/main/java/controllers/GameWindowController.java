package main.java.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import main.java.HTK.recording.TataiSpeechRecognizer;

public class GameWindowController extends TataiController implements Initializable {

	/* FXML Nodes */
	@FXML
	private Label _intLabel;
	@FXML
	private Label _translatedLabel;
	@FXML
	private Label _questionNoLabel;
	@FXML
	private Label _rightFeedbackLabel;
	@FXML
	private Label _leftFeedbackLabel;
	@FXML
	private Pane _pane;
	@FXML
	private Pane _childPane;
	@FXML
	private Button _recordButton;
	@FXML
	private Button _playbackButton;
	@FXML
	private Button _nextQuestionButton;
	@FXML
	private Button _tryAgainButton;
	@FXML
	private Button _skipButton;
	@FXML
	private FontAwesomeIconView _exitButton;
	@FXML
	private FontAwesomeIconView _rightIncorrectFeedbackIcon;
	@FXML
	private FontAwesomeIconView _leftIncorrectFeedbackIcon;
	@FXML
	private FontAwesomeIconView _rightCorrectFeedbackIcon;
	@FXML
	private FontAwesomeIconView _leftCorrectFeedbackIcon;

	/* Fields */
	private TataiSpeechRecognizer _speech;
	private ArrayList<String> _output;

	/**
	 * Handles pressing of the record button
	 */
	@FXML
	public void handleRecordClick() {
			// Temporarily remove unnecessary buttons and disable recording
			_playbackButton.setVisible(false);
			_nextQuestionButton.setVisible(false);
			_recordButton.setDisable(true);
			_recordButton.setText(RECORDING);

			// Create thread for recording process
			_speech = new TataiSpeechRecognizer();
			Task<Void> task = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					// Begin recording process
					_speech.record();

					return null;
				}

			};

			// Once recording task is complete reconfigure GUI
			task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
				@Override
				public void handle(WorkerStateEvent event) {
					// Reinstate play back and next question buttons and record ability
					_playbackButton.setVisible(true);
					_nextQuestionButton.setVisible(true);
					_recordButton.setDisable(false);
					_recordButton.setText(RE_RECORD);
					_nextQuestionButton.requestFocus();
				}
			});

			// Run thread
			Thread th = new Thread(task);
			th.start();
		
	}

	/**
	 * Handles pressing of play back button
	 */
	@FXML
	public void handlePlaybackClick() {
		// New thread for playing back from audio file
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				_speech.playback();
				return null;
			}
		};

		Thread th = new Thread(task);
		th.start();
		_nextQuestionButton.requestFocus();
	}

	/**
	 * Handles pressing of try again button
	 */
	@FXML
	public void handleTryAgainClick() {
		// Hide unnecessary buttons
		_tryAgainButton.setVisible(false);
		_skipButton.setVisible(false);

		// Display record button
		_recordButton.setVisible(true);
		_recordButton.requestFocus();
	}

	/**
	 * Handles pressing of submit button
	 */
	@FXML
	public void handleSubmitClick() {
		// If game state is onto last question set the nextQuestion button text to
		// indicate finishing
		if (Context.getInstance().currentGame().currentQuestion() - 1 == // game class increments q. no. one too soon
		Context.getInstance().currentGame().totalNumberOfQuestions()) {
			_nextQuestionButton.setText(FINISH);
		}

		// If button says next or finish call nextquesion()
		if (_nextQuestionButton.getText().equals(NEXT) || _nextQuestionButton.getText().equals(FINISH)) {
			nextQuestion();
		} else { // Else button says 'Submit', in which case undergo recording strategy
			// get readout from speech object
			_speech.readFile();
			_output = _speech.getText();
			if (isCorrect()) {
				// If question is deemed correct
				questionCorrect();
			} else {
				// If question is deemed incorrect
				questionIncorrect();
			}
		}

		// New thread for cleaning up folder contents
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				// Cleanup method for speech object
				_speech.cleanup();
				return null;
			}
		};

		Thread th = new Thread(task);
		th.start();
	}

	/**
	 * Handles pressing of quit button
	 */
	@FXML
	public void handleQuitClick() {
		// Prompt user with quit confirmation window

		boolean quit = showWarningDialogConfirmation(CONFIRMATION_QUIT_DIALOG, CONFIRMATION_QUIT_DIALOG_MESSAGE);

		if (quit) {
			// Quit the game
			quitCurrentGame();
		}
	}

	/**
	 * Method to quit current game gracefully
	 */
	public void quitCurrentGame() {
		Context.getInstance().currentGame().endGame();
		Context.getInstance().setGameToEquation();
		changeWindow(LEVEL_SELECT_FXML, _exitButton);
	}

	/**
	 * Determines correctness of answer given through speech
	 * 
	 * @return isCorrect : boolean
	 */
	private boolean isCorrect() {
		String answer = Context.getInstance().currentGame().translateCurrentQuestion();
		answer = answer.replace("mā", "maa");
		answer = answer.replace("whā", "whaa");
		answer = answer.replace("ā", "a");
		List<String> answerList = Arrays.asList(answer.split(" "));

		ArrayList<String> concatList = new ArrayList<String>();

		for (int i = 0; i < answerList.size(); i++) {
			for (int j = 0; j < _output.size(); j++) {
				if (_output.get(j).equals(answerList.get(i))) {
					concatList.add(_output.get(j));
					break;
				}
			}
		}

		if (concatList.equals(answerList)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method intended to be called when question is answered incorrectly, alters
	 * GUI appropriately to reflect answer.
	 */
	public void questionIncorrect() {
		// Set color scheme to be white text on red background
		_pane.setStyle("-fx-background-color: " + INCORRECT_RED);
		_intLabel.setStyle("-fx-text-fill: white");
		_questionNoLabel.setStyle("-fx-text-fill: white");

		// Give incorrect feedback
		_rightFeedbackLabel.setText(TRY_AGAIN);
		_leftFeedbackLabel.setText(WHOOPS);
		_rightIncorrectFeedbackIcon.setVisible(true);
		_leftIncorrectFeedbackIcon.setVisible(true);

		// Display skip/try again buttons
		_skipButton.setVisible(true);
		_tryAgainButton.setVisible(true);
		_tryAgainButton.requestFocus();
		// Remove record/playback buttons
		_nextQuestionButton.setVisible(false);
		_playbackButton.setVisible(false);
		_recordButton.setVisible(false);

		// If on second attempt hide skip/try again and show answer/next button
		if (Context.getInstance().currentGame().getAttempted()) {
			// Hide irrelevant buttons
			_skipButton.setVisible(false);
			_tryAgainButton.setVisible(false);

			// Give incorrect feedback
			_rightFeedbackLabel.setText(BAD_LUCK);
			_leftFeedbackLabel.setText(NOT_QUITE);

			// Show relevant buttons
			_nextQuestionButton.setVisible(true);
			_translatedLabel.setVisible(true);

			// If next button does not currently display finish, set text to next
			if (!_nextQuestionButton.getText().equals(FINISH)) {
				_nextQuestionButton.setText(NEXT);
				_nextQuestionButton.requestFocus();
			}
		}

		// Tell game object question incorrectly answered
		Context.getInstance().currentGame().answerQuestion(false);
	}

	/**
	 * Method intended to be called when question is answered correctly, alters GUI
	 * appropriately to reflect answer.
	 */
	public void questionCorrect() {
		// Tell game object question correctly answered
		Context.getInstance().currentGame().answerQuestion(true);

		// Give correct feedback
		_rightFeedbackLabel.setText(CORRECT);
		_leftFeedbackLabel.setText(CORRECT);
		_leftIncorrectFeedbackIcon.setVisible(false);
		_rightIncorrectFeedbackIcon.setVisible(false);
		_leftCorrectFeedbackIcon.setVisible(true);
		_rightCorrectFeedbackIcon.setVisible(true);

		// If shown, hide try again/skip and show answer
		_skipButton.setVisible(false);
		_tryAgainButton.setVisible(false);
		_translatedLabel.setVisible(true);
		_recordButton.setVisible(false);
		_playbackButton.setVisible(false);

		// Change color scheme to white text on green background
		_pane.setStyle("-fx-background-color: " + CORRECT_GREEN);
		_intLabel.setStyle("-fx-text-fill: white");
		_questionNoLabel.setStyle("-fx-text-fill: white");

		if (!_nextQuestionButton.getText().equals(FINISH)) {
			_nextQuestionButton.setText(NEXT);
			_nextQuestionButton.requestFocus();
		}
	}

	/**
	 * Handles user requesting to change view
	 */
	public void nextQuestion() {
		// If button text says finish, finish game.
		if (_nextQuestionButton.getText().equals(FINISH)) {
			changeWindow(RESULTS_FXML, _nextQuestionButton); // Change to ResultsWindow.fxml view
		} else {
			changeWindow(GAME_FXML, _nextQuestionButton); // Change to GameWindow.fxml view
		}
	}

	/**
	 * Initializes state of the scene on open
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Hide irrelevant buttons / icons
		_playbackButton.setVisible(false);
		_skipButton.setVisible(false);
		_tryAgainButton.setVisible(false);
		_nextQuestionButton.setVisible(false);
		_leftIncorrectFeedbackIcon.setVisible(false);
		_rightIncorrectFeedbackIcon.setVisible(false);
		_leftCorrectFeedbackIcon.setVisible(false);
		_rightCorrectFeedbackIcon.setVisible(false);

		// Set answer label with correct answer and hide
		_translatedLabel.setText(Context.getInstance().currentGame().translateCurrentQuestion());
		_translatedLabel.setVisible(false);

		// Display question integer
		Context.getInstance().currentGame().displayCurrentQuestion(_intLabel, _pane);
		_childPane.setBackground(_pane.getBackground());

		// Set question number label
		_questionNoLabel.setText(Context.getInstance().currentGame().currentQuestion() + "/"
				+ Context.getInstance().currentGame().totalNumberOfQuestions());

		String style = _intLabel.getStyle();
		_questionNoLabel.setStyle(style);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				_recordButton.requestFocus();
			}
		});
	}

	/**
	 * Handles key bindings for window
	 * 
	 * @param e
	 *            : KeyEvent
	 */
	@FXML
	public void handleKeyPress(KeyEvent e) {
		if (e.getCode() == KeyCode.ESCAPE) {
			handleQuitClick();
		}
	}
}
