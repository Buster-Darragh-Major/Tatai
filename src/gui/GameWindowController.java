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
	public void handleIncorrectClick() {
		_pane.setStyle("-fx-background-color: #f73333");
		_intLabel.setTextFill(Color.WHITE);
		_questionNoLabel.setTextFill(Color.WHITE);
		
		_nextQuestionButton.setTextFill(Color.web("#f73333"));
		_nextQuestionButton.setStyle("-fx-background-color: #ffffff");
		_nextQuestionButton.setVisible(true);
		
		Context.getInstance().currentGame().nextQuestion();
	}
	
	@FXML
	public void handleCorrectClick() {
		_pane.setStyle("-fx-background-color: #00d10a");
		_intLabel.setTextFill(Color.WHITE);
		_questionNoLabel.setTextFill(Color.WHITE);
		
		_nextQuestionButton.setTextFill(Color.web("#00d10a"));
		_nextQuestionButton.setStyle("-fx-background-color: #ffffff");
		_nextQuestionButton.setVisible(true);

		Context.getInstance().currentGame().nextQuestion();
	}
	
	@FXML
	public void handleNextQuestionClick() {
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
		_nextQuestionButton.setVisible(false);
		Context.getInstance().currentGame().displayCurrentQuestion(_intLabel, _pane);
		
		_questionNoLabel.setText(Context.getInstance().currentGame().currentQuestion() + "/10");
		_questionNoLabel.setTextFill(_intLabel.getTextFill());
	}
}
