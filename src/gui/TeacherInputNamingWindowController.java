package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import questionlist.TextQuestionListHandler;

public class TeacherInputNamingWindowController extends TataiController implements Initializable {

	@FXML private Button _exitButton;
	@FXML private Button _createButton;
	@FXML private Button _editButton;
	@FXML private Label _warningLabel;
	@FXML private JFXTextField _textField;
	
	@FXML
	public void handleExitCick() {
		Stage stage = (Stage) _exitButton.getScene().getWindow(); // Get Current stage
		changeWindow("MainWindow.fxml", stage);// Change to MainWindow.fxml view
	}
	
	@FXML
	public void handleEditClick() {
		try {
			FXMLLoader fxml = new FXMLLoader(getClass().getResource("CustomListEditView.fxml"));
			Parent root = (Parent) fxml.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			Scene scene = new Scene(root);
			scene.getRoot().requestFocus();
			stage.setTitle("Edit Lists");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
	public void handleKeyPress(KeyEvent e) {
		if (e.getCode() == KeyCode.ESCAPE) {
			handleExitCick();
		} else if (e.getCode() == KeyCode.ENTER) {
			handleCreateClick();
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
