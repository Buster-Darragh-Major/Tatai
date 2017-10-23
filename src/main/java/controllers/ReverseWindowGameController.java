package main.java.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.game.Level;

public class ReverseWindowGameController extends TataiController implements Initializable {

	/* Macros */
	public final static String AUDIO_FILE_PATH = "src/main/resources/audio/NumberRecordings/";
	
	/* FXML Nodes */
	@FXML private Pane _pane;
	@FXML private Label _wordLabel;
	@FXML private Label _intLabel;
	@FXML private Label _questionNoLabel;
	@FXML private FontAwesomeIconView _exitButton;
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
	@FXML FontAwesomeIconView _hearButton;
	
	/* Fields */
	private String _answer;
	
	@FXML
	public void handle0Click() {
		addToLabel("0");
	}
	
	@FXML
	public void handle1Click() {
		addToLabel("1");
		_submitButton.setDisable(false);
	}
	
	@FXML
	public void handle2Click() {
		addToLabel("2");
		_submitButton.setDisable(false);
	}
	
	@FXML
	public void handle3Click() {
		addToLabel("3");
		_submitButton.setDisable(false);
	}
	
	@FXML
	public void handle4Click() {
		addToLabel("4");
		_submitButton.setDisable(false);
	}
	
	@FXML
	public void handle5Click() {
		addToLabel("5");
		_submitButton.setDisable(false);
	}
	
	@FXML
	public void handle6Click() {
		addToLabel("6");
		_submitButton.setDisable(false);
	}
	
	@FXML
	public void handle7Click() {
		addToLabel("7");
		_submitButton.setDisable(false);
	}
	
	@FXML
	public void handle8Click() {
		addToLabel("8");
		_submitButton.setDisable(false);
	}
	
	@FXML
	public void handle9Click() {
		addToLabel("9");
		_submitButton.setDisable(false);
	}
	
	@FXML
	public void handleDeleteClick() {
		_intLabel.setText("");
		_submitButton.setDisable(true);
	}
	
	@FXML
	public void handlesubmitClick() {
		if (_submitButton.getText().equals("Submit")) {
			submit();
		} else {
			next();
		}
	}
	
	@FXML
	public void handleHearClick() {
		String number = _wordLabel.getText();
		number = number.replaceAll("ā", "a");
		String command = "aplay " + number + ".wav";
		
		ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", command);
		builder.directory(new File(AUDIO_FILE_PATH));
		
		@SuppressWarnings("unused")
		Process process;
		try {
			process = builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void submit() {
		if (_intLabel.getText().equals(_answer)) {
			_submitButton.setStyle("-fx-background-color: white;" + " -fx-text-fill: " + CORRECT_GREEN);
			questionCorrect();
		} else {
			_submitButton.setStyle("-fx-background-color: white;" + " -fx-text-fill: " + INCORRECT_RED);
			questionIncorrect();
		}
		
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
			_submitButton.setText("Finish");
		} else {
			_submitButton.setText("Next");
		}
	}
	
	/**
	 * Handles user requesting to change view
	 */
	public void next() {
		Stage stage = (Stage) _submitButton.getScene().getWindow(); //Get current stage
		
		// If button text says finish, finish game.
		if (_submitButton.getText().equals("Finish")) {
			changeWindow("ResultsWindow.fxml", stage); // Change to ResultsWindow.fxml view
		} else {
			changeWindow("ReverseGamemodeWindow.fxml", stage); // Change to GameWindow.fxml view
		}
	}
	
	private void addToLabel(String num) {
		if (_intLabel.getText().length() == 2) {
			flashText(_intLabel);
		} else if (_intLabel.getText().equals("0")) {
			_intLabel.setText(num);
		} else {
			_intLabel.setText(_intLabel.getText() + num);
		}
	}
	
	private void questionCorrect() {
		// Tell game object question correctly answered
		Context.getInstance().currentGame().answerQuestion(true);
		
		// Change color scheme to white text on green background
		_pane.setStyle("-fx-background-color: " + CORRECT_GREEN);
		_intLabel.setStyle("-fx-text-fill: white");
		_questionNoLabel.setStyle("-fx-text-fill: white");
		_wordLabel.setStyle("-fx-text-fill: white");
	}
	
	private void questionIncorrect() {
		// Tell game object question correctly answered
		Context.getInstance().currentGame().answerQuestion(false);
		
		// Change color scheme to white text on green background
		_pane.setStyle("-fx-background-color: " + INCORRECT_RED);
		_intLabel.setStyle("-fx-text-fill: white");
		_questionNoLabel.setStyle("-fx-text-fill: white");
		_wordLabel.setStyle("-fx-text-fill: white");
	}
	
	@FXML
	public void handleExitClick() {
		// Prompt user with quit confirmation window 
		boolean quit = showWarningDialogConfirmation("Confirmation Dialog", "Are you sure you want to quit your current game");
		
		if (quit) {
			// Quit the game
			quitCurrentGame();
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
		
		_0.setStyle("-fx-background-color: " + style);
		_1.setStyle("-fx-background-color: " + style);
		_2.setStyle("-fx-background-color: " + style);
		_3.setStyle("-fx-background-color: " + style);
		_4.setStyle("-fx-background-color: " + style);
		_5.setStyle("-fx-background-color: " + style);
		_6.setStyle("-fx-background-color: " + style);
		_7.setStyle("-fx-background-color: " + style);
		_8.setStyle("-fx-background-color: " + style);
		_9.setStyle("-fx-background-color: " + style);
		
		_clearButton.setStyle("-fx-background-color: " + style);
		_submitButton.setStyle("-fx-background-color: " + style);
		
		if (Context.getInstance().currentGame().currentLevel() == Level.LEVEL2_REVERSE) {
			_hearButton.setVisible(false);
		}
	}
}
