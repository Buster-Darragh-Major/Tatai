package gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXHamburger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameWindowController extends TataiController implements Initializable{
	
	private static final String INCORRECT_RED = "#f73333";
	private static final String CORRECT_GREEN = "#00d10a";
	private static final String WHITE = "#ffffff";
	
	private boolean _finalAttempt = false;
	
	@FXML
	private Label _intLabel;
	@FXML
	private Label _translatedLabel;
	@FXML
	private Label _questionNoLabel;
	
	@FXML
	private Pane _pane;
	
	@FXML
	private JFXHamburger _mainMenuButton;
	@FXML
	private Button _correctButton;
	@FXML
	private Button _incorretButton;
	@FXML
	private Button _nextQuestionButton;
	@FXML
	private Button _tryAgainButton;
	@FXML
	private Button _skipButton;
	
	@FXML 
	public void handleIncorrectClick() {
		_pane.setStyle("-fx-background-color: " + INCORRECT_RED);
		_intLabel.setTextFill(Color.WHITE);
		_questionNoLabel.setTextFill(Color.WHITE);
		
		_skipButton.setVisible(true);
		_tryAgainButton.setVisible(true);

		
		if (_finalAttempt) {
			_skipButton.setVisible(false);
			_tryAgainButton.setVisible(false);
			
			_translatedLabel.setText(Context.getInstance().currentGame().translateCurrentQuestion());
			
			_nextQuestionButton.setTextFill(Color.web(INCORRECT_RED));
			_nextQuestionButton.setStyle("-fx-background-color: " + WHITE);
			_nextQuestionButton.setVisible(true);
		}
		_finalAttempt = true;
	}
	
	@FXML
	public void handleCorrectClick() {
		_skipButton.setVisible(false);
		_tryAgainButton.setVisible(false);
		
		_pane.setStyle("-fx-background-color: " + CORRECT_GREEN);
		_intLabel.setTextFill(Color.WHITE);
		_translatedLabel.setText(Context.getInstance().currentGame().translateCurrentQuestion());
		_questionNoLabel.setTextFill(Color.WHITE);
		
		_nextQuestionButton.setTextFill(Color.web(CORRECT_GREEN));
		_nextQuestionButton.setStyle("-fx-background-color: " + WHITE);
		_nextQuestionButton.setVisible(true);
	}
	
	@FXML
	public void handleNextQuestionClick() {
		// TODO BUSTER CHANGE THIS
		//Context.getInstance().currentGame().nextQuestion();
		Stage stage = (Stage) _nextQuestionButton.getScene().getWindow(); //Get current stage
		changeWindow("GameWindow.fxml", stage); // Change to GameWindow.fxml view
	}
	
	@FXML
	public void handleMainMenuClick() {
		Stage stage = (Stage) _mainMenuButton.getScene().getWindow(); // Get current stage
		changeWindow("MainWindow.fxml", stage); // Change to MainWindow.fxml view
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_skipButton.setVisible(false);
		_tryAgainButton.setVisible(false);
		_nextQuestionButton.setVisible(false);
		Context.getInstance().currentGame().displayCurrentQuestion(_intLabel, _pane);
		
		_questionNoLabel.setText(Context.getInstance().currentGame().currentQuestion() + "/10");
		_questionNoLabel.setTextFill(_intLabel.getTextFill());
	}
}
