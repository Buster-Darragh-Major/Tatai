package gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TeacherInputNamingWindowController extends TataiController implements Initializable {

	@FXML private Button _exitButton;
	@FXML private Button _createButton;
	@FXML private JFXTextField _textField;
	
	@FXML
	public void handleExitCick() {
		Stage stage = (Stage) _exitButton.getScene().getWindow(); // Get Current stage
		changeWindow("MainWindow.fxml", stage);// Change to MainWindow.fxml view
	}
	
	@FXML
	public void handleTextFieldKeystroke() {
		if (_textField.getText().equals("")) {
			_createButton.setDisable(true);
		} else {
			_createButton.setDisable(false);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_createButton.setDisable(true);
	}
	
}
