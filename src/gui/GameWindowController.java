package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import HTK.recording.TataiSpeechRecognizer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tatai.game.TataiGame;

public class GameWindowController extends TataiController implements Initializable{

private static final String FINISH = "Finish!";
	
	/* FXML Nodes */
	@FXML
	private Label _intLabel;
	@FXML
	private Label _translatedLabel;
	@FXML
	private Label _questionNoLabel;
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
	private Button _exitButton;
	
	private TataiSpeechRecognizer _speech;
	private ArrayList<String> _output;
	
	/**
	 * Stub for handling the incorrect click (Will be replaced with HTK logic)
	 */
	@FXML 
	public void handleIncorrectClick() {
		// Set color scheme to be white text on red background
		_pane.setStyle("-fx-background-color: " + INCORRECT_RED);
		_intLabel.setTextFill(Color.WHITE);
		_questionNoLabel.setTextFill(Color.WHITE);
		
		// Display skip/try again buttons
		_skipButton.setVisible(true);
		_tryAgainButton.setVisible(true);

		// If on second attempt hide skip/try again and show answer/next button
		if (Context.getInstance().currentGame().getAttempted()) {
			_skipButton.setVisible(false);
			_tryAgainButton.setVisible(false);
			
			_translatedLabel.setVisible(true);
			
			_nextQuestionButton.setVisible(true);
		}
		
		// Tell game object question incorrectly answered
		Context.getInstance().currentGame().answerQuestion(false);
	}
	
	/**
	 * Stub for handling the correct click (Will be replaced with HTK logic)
	 */
	@FXML
	public void handleCorrectClick() {
		// Tell game object question correctly answered
		Context.getInstance().currentGame().answerQuestion(true);
		
		// If shown, hide try again/skip and show answer
		_skipButton.setVisible(false);
		_tryAgainButton.setVisible(false);
		_translatedLabel.setVisible(true);
		
		// Change color scheme to white text on green background
		_pane.setStyle("-fx-background-color: " + CORRECT_GREEN);
		_intLabel.setTextFill(Color.WHITE);
		_questionNoLabel.setTextFill(Color.WHITE);

		// Show next Button
		_nextQuestionButton.setVisible(true);
	}
	
	/**
	 * Handles user requesting to change view
	 */
	@FXML
	public void handleNextQuestionClick() {
		Stage stage = (Stage) _nextQuestionButton.getScene().getWindow(); //Get current stage
		
		// If button text says finish, finish game.
		if (_nextQuestionButton.getText() == FINISH) {
			changeWindow("ResultsWindow.fxml", stage); // Change to ResultsWindow.fxml view
		} else {
			changeWindow("GameWindow.fxml", stage); // Change to GameWindow.fxml view
		}
	}
	
	@FXML
	public void handleQuitClick() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Quit Current Game");
		alert.setContentText("Are you sure you want to quit your current game?");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			quitCurrentGame();
		} else {
			alert.close();
		}
	}
	
	public void quitCurrentGame() {
		Stage stage = (Stage) _exitButton.getScene().getWindow();
		Context.getInstance().currentGame().endGame();
		changeWindow("LevelSelectWindow.fxml", stage);
	}
	
	@FXML
	public void handleRecordClick() {
		_speech = new TataiSpeechRecognizer();
		_speech.record();
		
		_nextQuestionButton.setVisible(true);
	}
	
	@FXML
	public void handlePlaybackClick() {
		try {
			_speech.playback();
		} catch (RuntimeException e) {
		}
	}
	
	@FXML
	public void handleSubmitClick() {
		_speech.readFile();
		_output = _speech.getText();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Context.getInstance().currentGame();
		if (Context.getInstance().currentGame().currentQuestion() == 
				TataiGame.TOTAL_NUMBER_OF_QUESTIONS) {
			_nextQuestionButton.setText(FINISH);
		}
		
		// Hide skip/try again/next question
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
		_questionNoLabel.setText(Context.getInstance().currentGame().currentQuestion() + "/10");
		_questionNoLabel.setTextFill(_intLabel.getTextFill());
	}
}
