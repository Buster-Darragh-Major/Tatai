package gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import questionlist.TextQuestionListHandler;

public class TeacherInputNamingWindowController extends TataiController implements Initializable {

	@FXML private Button _exitButton;
	@FXML private Button _createButton;
	@FXML private Label _warningLabel;
	@FXML private JFXTextField _textField;
	
	@FXML
	public void handleExitCick() {
		Stage stage = (Stage) _exitButton.getScene().getWindow(); // Get Current stage
		changeWindow("MainWindow.fxml", stage);// Change to MainWindow.fxml view
	}
	
	@FXML
	public void handleTextFieldKeystroke() {
		TextQuestionListHandler handler = new TextQuestionListHandler(_textField.getText());
		
		if (_textField.getText().equals("")) {
			_warningLabel.setVisible(false);
			_createButton.setDisable(true);
		} else if (handler.alreadyExists()) {
			_warningLabel.setVisible(true);
			_createButton.setDisable(true);
		} else {
			_warningLabel.setVisible(false);
			_createButton.setDisable(false);
		}
	}
	
	@FXML
	public void handleCreateClick() {
		Context.getInstance().setQuestionList(_textField.getText());
		
		Stage stage = (Stage) _createButton.getScene().getWindow(); // Get Current stage
		changeWindow("TeacherInputWindow.fxml", stage);// Change to MainWindow.fxml view
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_warningLabel.setVisible(false);
		_createButton.setDisable(true);
	}
	
}
