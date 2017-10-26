package main.java.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
import main.java.game.Level;

public class ReverseWindowGameController extends TataiController implements Initializable {

	/* Macros */
	public static final String AUDIO_FILE_PATH = "/main/resources/audio/NumberRecordings/";
	public static final String FX_BACKGROUND_COLOR = "-fx-background-color: ";
	public static final String WAV = ".wav";
	
	/* FXML Nodes */
	@FXML private Pane _pane;
	@FXML private Label _wordLabel;
	@FXML private Label _intLabel;
	@FXML private Label _questionNoLabel;
	@FXML private Button _0;
	@FXML private Button _1;
	@FXML private Button _2;
	@FXML private Button _3;
	@FXML private Button _4;
	@FXML private Button _5;
	@FXML private Button _6;
	@FXML private Button _7;
	@FXML private Button _8;
	@FXML private Button _9;
	@FXML private Button _clearButton;
	@FXML private Button _submitButton;
	@FXML private FontAwesomeIconView _hearButton;
	@FXML private FontAwesomeIconView _exitButton;
	@FXML private FontAwesomeIconView _rightIncorrectFeedbackIcon;
	@FXML private FontAwesomeIconView _leftIncorrectFeedbackIcon;
	@FXML private FontAwesomeIconView _rightCorrectFeedbackIcon;
	@FXML private FontAwesomeIconView _leftCorrectFeedbackIcon;
	@FXML private Label _rightFeedbackLabel;
	@FXML private Label _leftFeedbackLabel;

	
	/* Fields */
	private String _answer;
	
	/**
	 * The following methods handle input from on screen keyboard
	 */
	@FXML
	public void handle0Click() {
		addToLabel(ZERO_TEXT);
	}
	
	@FXML
	public void handle1Click() {
		addToLabel(ONE_TEXT);
		_submitButton.setDisable(false);
	}
	
	@FXML
	public void handle2Click() {
		addToLabel(TWO_TEXT);
		_submitButton.setDisable(false);
	}
	
	@FXML
	public void handle3Click() {
		addToLabel(THREE_TEXT);
		_submitButton.setDisable(false);
	}
	
	@FXML
	public void handle4Click() {
		addToLabel(FOUR_TEXT);
		_submitButton.setDisable(false);
	}
	
	@FXML
	public void handle5Click() {
		addToLabel(FIVE_TEXT);
		_submitButton.setDisable(false);
	}
	
	@FXML
	public void handle6Click() {
		addToLabel(SIX_TEXT);
		_submitButton.setDisable(false);
	}
	
	@FXML
	public void handle7Click() {
		addToLabel(SEVEN_TEXT);
		_submitButton.setDisable(false);
	}
	
	@FXML
	public void handle8Click() {
		addToLabel(EIGHT_TEXT);
		_submitButton.setDisable(false);
	}
	
	@FXML
	public void handle9Click() {
		addToLabel(NINE_TEXT);
		_submitButton.setDisable(false);
	}
	
	@FXML
	public void handleDeleteClick() {
		_intLabel.setText("");
		_submitButton.setDisable(true);
	}
	
	@FXML
	public void handlesubmitClick() {
		if (_submitButton.getText().equals(SUBMIT)) {
			submit();
		} else {
			next();
		}
	}
	
	/**
	 * Handles user pressing speaker button, where number is read out to them
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 * @throws LineUnavailableException 
	 * @throws URISyntaxException 
	 */
	@FXML
	public void handleHearClick() throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {
		// Get number file format name
		String number = _wordLabel.getText();
		number = number.replaceAll("ā", "a");
		
		URL sound = getClass().getResource(AUDIO_FILE_PATH + number + WAV);
		
		// Start audio playback, disable sound button for duration
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sound);
		Clip clip = AudioSystem.getClip();
		
		_hearButton.setDisable(true);
		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				clip.open(audioInputStream);
				clip.start();
				
				return null;
			}
		};
		
		// Re enable sound button functionality on success
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				_hearButton.setDisable(false);
			}
		});
		
		startBackgroundThread(task);
	}
	
	/**
	 * Handles user submitting answer typed on keyboard, compares with untranslated integer from Maori string
	 */
	private void submit() {
		if (_intLabel.getText().equals(_answer)) {
			_submitButton.setStyle("-fx-background-color: white;" + " -fx-text-fill: " + CORRECT_GREEN);
			questionCorrect();
		} else {
			_submitButton.setStyle("-fx-background-color: white;" + " -fx-text-fill: " + INCORRECT_RED);
			questionIncorrect();
		}
		
		// Hide keyboard
		_0.setVisible(false);
		_1.setVisible(false);
		_2.setVisible(false);
		_3.setVisible(false);
		_4.setVisible(false);
		_5.setVisible(false);
		_6.setVisible(false);
		_7.setVisible(false);
		_8.setVisible(false);
		_9.setVisible(false);
		_clearButton.setVisible(false);
		
		// If game state is onto last question set the nextQuestion button text to indicate finishing
		if (Context.getInstance().currentGame().currentQuestion() - 1 == // game class increments q. no. one too soon
				Context.getInstance().currentGame().totalNumberOfQuestions()) {
			_submitButton.setText(FINISH);
		} else {
			_submitButton.setText(NEXT);
		}
	}
	
	/**
	 * Handles user requesting to change view
	 */
	public void next() {

		// If button text says finish, finish game.
		if (_submitButton.getText().equals(FINISH)) {
			changeWindow(RESULTS_FXML, _submitButton); // Change to ResultsWindow.fxml view
		} else {
			changeWindow(REVERSE_GAME_FXML, _submitButton); 
		}
	}
	
	/**
	 * Determines whether appropriate to add to label given current text and current input
	 * @param num
	 */
	private void addToLabel(String num) {
		if (!_0.isVisible()) { // If keyboard non visible do not allow
			return;
		}
		
		// Only allow if length < 2
		if (_intLabel.getText().length() == 2) {
			flashText(_intLabel);
		} else if (_intLabel.getText().equals(ZERO_TEXT)) {
			_intLabel.setText(num);
		} else {
			_intLabel.setText(_intLabel.getText() + num);
		}
	}
	
	/**
	 * Gives visual feedback on answer correctness
	 */
	private void questionCorrect() {
		// Tell game object question correctly answered
		Context.getInstance().currentGame().answerQuestion(true);
		
		// Change color scheme to white text on green background
		_pane.setStyle("-fx-background-color: " + CORRECT_GREEN);
		_intLabel.setStyle("-fx-text-fill: white");
		_questionNoLabel.setStyle("-fx-text-fill: white");
		_wordLabel.setStyle("-fx-text-fill: white");
		_hearButton.setStyle("-fx-fill: white");
		
		// Give user feedback
		_leftFeedbackLabel.setText(CORRECT);
		_rightFeedbackLabel.setText(CORRECT);
		_leftCorrectFeedbackIcon.setVisible(true);
		_rightCorrectFeedbackIcon.setVisible(true);
	}
	
	private void questionIncorrect() {
		// Tell game object question correctly answered
		Context.getInstance().currentGame().answerQuestion(false);
		
		// Change color scheme to white text on green background
		_pane.setStyle("-fx-background-color: " + INCORRECT_RED);
		_intLabel.setStyle("-fx-text-fill: white");
		_questionNoLabel.setStyle("-fx-text-fill: white");
		_wordLabel.setStyle("-fx-text-fill: white");
		_hearButton.setStyle("-fx-fill: white");
		
		// Give user feedback
		_leftFeedbackLabel.setText(BAD_LUCK);
		_rightFeedbackLabel.setText(NOT_QUITE);
		_leftIncorrectFeedbackIcon.setVisible(true);
		_rightIncorrectFeedbackIcon.setVisible(true);
	}
	
	/**
	 * Handles user pressing exit button
	 */
	@FXML
	public void handleExitClick() {
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
		changeWindow(LEVEL_SELECT_FXML, _exitButton);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_submitButton.setDisable(true);
		
		// Set answer label with correct answer and hide
		_wordLabel.setText(Context.getInstance().currentGame().translateCurrentQuestion());
		
		// Display question integer
		Context.getInstance().currentGame().displayCurrentQuestion(_intLabel, _pane);
		_answer = _intLabel.getText();
		_intLabel.setText("");
		
		// Set question number label
		_questionNoLabel.setText(Context.getInstance().currentGame().currentQuestion() + "/" + 
				Context.getInstance().currentGame().totalNumberOfQuestions());
		
		String style = _intLabel.getStyle();
		_questionNoLabel.setStyle(style);
		_wordLabel.setStyle(style);
		
		style = style.split(": ")[1];
		
		_0.setStyle(FX_BACKGROUND_COLOR + style);
		_1.setStyle(FX_BACKGROUND_COLOR + style);
		_2.setStyle(FX_BACKGROUND_COLOR + style);
		_3.setStyle(FX_BACKGROUND_COLOR + style);
		_4.setStyle(FX_BACKGROUND_COLOR + style);
		_5.setStyle(FX_BACKGROUND_COLOR + style);
		_6.setStyle(FX_BACKGROUND_COLOR + style);
		_7.setStyle(FX_BACKGROUND_COLOR + style);
		_8.setStyle(FX_BACKGROUND_COLOR + style);
		_9.setStyle(FX_BACKGROUND_COLOR + style);
		
		_hearButton.setStyle("-fx-fill: " + style);
		_clearButton.setStyle(FX_BACKGROUND_COLOR + style);
		_submitButton.setStyle(FX_BACKGROUND_COLOR + style);
		
		if (Context.getInstance().currentGame().currentLevel() == Level.LEVEL2_REVERSE) {
			_hearButton.setVisible(false);
		}
		
		// Hide feedback icons
		_leftCorrectFeedbackIcon.setVisible(false);
		_rightCorrectFeedbackIcon.setVisible(false);
		_leftIncorrectFeedbackIcon.setVisible(false);
		_rightIncorrectFeedbackIcon.setVisible(false);
		
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	_submitButton.requestFocus();
	        }
	    });
	}
	
	/**
	 * Handles key presses
	 * @param e The Key Event
	 */
	@FXML public void onKeyPress(KeyEvent e) {
		KeyCode code = e.getCode();
		if (code == KeyCode.ESCAPE) {
			handleExitClick();
		} else if (code == KeyCode.DIGIT0 || code == KeyCode.NUMPAD0) {
			handle0Click();
		} else if (code == KeyCode.DIGIT1 || code == KeyCode.NUMPAD1) {
			handle1Click();
		} else if (code == KeyCode.DIGIT2 || code == KeyCode.NUMPAD2) {
			handle2Click();
		} else if (code == KeyCode.DIGIT3 || code == KeyCode.NUMPAD3) {
			handle3Click();
		} else if (code == KeyCode.DIGIT4 || code == KeyCode.NUMPAD4) {
			handle4Click();
		} else if (code == KeyCode.DIGIT5 || code == KeyCode.NUMPAD5) {
			handle5Click();
		} else if (code == KeyCode.DIGIT6 || code == KeyCode.NUMPAD6) {
			handle6Click();
		} else if (code == KeyCode.DIGIT7 || code == KeyCode.NUMPAD7) {
			handle7Click();
		} else if (code == KeyCode.DIGIT8 || code == KeyCode.NUMPAD8) {
			handle8Click();
		} else if (code == KeyCode.DIGIT9 || code == KeyCode.NUMPAD9) {
			handle9Click();
		} else if (code == KeyCode.BACK_SPACE) {
			handleDeleteClick();
		} else if ((code == KeyCode.SPACE || code == KeyCode.ENTER) && !_submitButton.isDisable()) {
			handlesubmitClick();
		}
	}
}
