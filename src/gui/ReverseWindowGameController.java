package gui;

import java.net.URL;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ReverseWindowGameController extends TataiController implements Initializable {

	@FXML private Pane _pane;
	@FXML private Label _wordLabel;
	@FXML private Label _intLabel;
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
	
	@FXML
	public void handleExitClick() {
		Context.getInstance().currentGame().endGame();
		
		Stage stage = (Stage) _exitButton.getScene().getWindow(); // Get Current stage
		changeWindow("LevelSelectWindow.fxml", stage);// Change to StatsWindow.fxml view
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_submitButton.setDisable(true);
		
		// Set answer label with correct answer and hide
		_wordLabel.setText(Context.getInstance().currentGame().translateCurrentQuestion());
		
		// Display question integer
		Context.getInstance().currentGame().displayCurrentQuestion(_intLabel, _pane);
		//_childPane.setBackground(_pane.getBackground());
		
		// Set question number label
		//_questionNoLabel.setText(Context.getInstance().currentGame().currentQuestion() + "/" + 
		//		Context.getInstance().currentGame().totalNumberOfQuestions());
		
		String style = _intLabel.getStyle();
		//_questionNoLabel.setStyle(style);
	}
}
