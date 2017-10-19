package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import HTK.recording.TataiSpeechRecognizer;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameWindowController extends TataiController implements Initializable{

private static final String FINISH = "Finish!";
	
	// TODO ***************************************************************
	// make play back and submit buttons disabled while recording occurs
	// TODO ***************************************************************

	/* FXML Nodes */
	@FXML private Label _intLabel;
	@FXML private Label _translatedLabel;
	@FXML private Label _questionNoLabel;
	@FXML private Pane _pane;
	@FXML private Pane _childPane;
	@FXML private Button _recordButton;
	@FXML private Button _playbackButton;
	@FXML private Button _nextQuestionButton;
	@FXML private Button _tryAgainButton;
	@FXML private Button _skipButton;
	@FXML private Button _exitButton;
	
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
		_recordButton.setText("Recording...");
		
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
				_recordButton.setText("Re Record");
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
		// If game state is onto last question set the nextQuestion button text to indicate finishing
		if (Context.getInstance().currentGame().currentQuestion() - 1 == // game class increments q. no. one too soon
				Context.getInstance().currentGame().totalNumberOfQuestions()) {
			_nextQuestionButton.setText(FINISH);
		}
		
		// If button says next or finish call nextquesion()
		if (_nextQuestionButton.getText().equals(NEXT) || 
				_nextQuestionButton.getText().equals(FINISH)) {
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
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Quit Current Game");
		alert.setContentText("Are you sure you want to quit your current game?");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			// Quit the game
			quitCurrentGame();
		} else {
			// Close alert
			alert.close();
		}
	}
	
	/**
	 * Method to quit current game gracefully
	 */
	public void quitCurrentGame() {
		Stage stage = (Stage) _exitButton.getScene().getWindow();
		Context.getInstance().currentGame().endGame();
		changeWindow("LevelSelectWindow.fxml", stage);
	}
	
	// TODO ***********************
	// Move this into game class
	// TODO ***********************
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
	 * Method intended to be called when question is answered incorrectly, alters GUI appropriately
	 * to reflect answer.
	 */ 
	public void questionIncorrect() {
		// Set color scheme to be white text on red background
		_pane.setStyle("-fx-background-color: " + INCORRECT_RED);
		_intLabel.setTextFill(Color.WHITE);
		_questionNoLabel.setTextFill(Color.WHITE);
		
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
	 * Method intended to be called when question is answered correctly, alters GUI appropriately
	 * to reflect answer.
	 */
	public void questionCorrect() {
		// Tell game object question correctly answered
		Context.getInstance().currentGame().answerQuestion(true);
		
		// If shown, hide try again/skip and show answer
		_skipButton.setVisible(false);
		_tryAgainButton.setVisible(false);
		_translatedLabel.setVisible(true);
		_recordButton.setVisible(false);
		_playbackButton.setVisible(false);
		
		// Change color scheme to white text on green background
		_pane.setStyle("-fx-background-color: " + CORRECT_GREEN);
		_intLabel.setTextFill(Color.WHITE);
		_questionNoLabel.setTextFill(Color.WHITE);

		if (!_nextQuestionButton.getText().equals(FINISH)) {
			_nextQuestionButton.setText(NEXT);
			_nextQuestionButton.requestFocus();
		}
	}
	
	/**
	 * Handles user requesting to change view
	 */
	public void nextQuestion() {
		Stage stage = (Stage) _nextQuestionButton.getScene().getWindow(); //Get current stage
		
		// If button text says finish, finish game.
		if (_nextQuestionButton.getText().equals(FINISH)) {
			changeWindow("ResultsWindow.fxml", stage); // Change to ResultsWindow.fxml view
		} else {
			changeWindow("GameWindow.fxml", stage); // Change to GameWindow.fxml view
		}
	}
	
	/**
	 * Initializes state of the scene on open
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Context.getInstance().currentGame();
		
		// Hide irrelevant buttons
		_playbackButton.setVisible(false);
		_skipButton.setVisible(false);
		_tryAgainButton.setVisible(false);
		_nextQuestionButton.setVisible(false);
		
		// Set answer label with correct answer and hide
		_translatedLabel.setText(Context.getInstance().currentGame().translateCurrentQuestion());
		_translatedLabel.setVisible(false);
		
		// Display question integer
		Context.getInstance().currentGame().displayCurrentQuestion(_intLabel, _pane);
		_childPane.setBackground(_pane.getBackground());
		
		// Set question number label
		_questionNoLabel.setText(Context.getInstance().currentGame().currentQuestion() + "/" + 
				Context.getInstance().currentGame().totalNumberOfQuestions());
		_questionNoLabel.setTextFill(_intLabel.getTextFill());
		
	    Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	_recordButton.requestFocus();
	        }
	    });
	}
	
	@FXML 
	public void handleKeyPress(KeyEvent e) {
		if (e.getCode() == KeyCode.ESCAPE) {
			handleQuitClick();
		} 
	}
}
