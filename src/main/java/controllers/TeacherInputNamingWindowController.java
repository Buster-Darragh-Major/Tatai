package main.java.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import main.java.questionlist.TextQuestionListHandler;

public class TeacherInputNamingWindowController extends TataiController implements Initializable {
	/* MACROS */
	public static final String TITLE = "Edit Lists";
	public static final int MAX_VALUE = 30;
	
	/* FXML Nodes */
	@FXML private FontAwesomeIconView _exitButton;
	@FXML private Button _createButton;
	@FXML private Button _editButton;
	@FXML private Label _warningLabel;
	@FXML private JFXTextField _textField;
	
	/**
	 * Handles user pressing exit button
	 */
	@FXML
	public void handleExitCick() {
		changeWindow(MAIN_FXML, _exitButton);// Change to MainWindow.fxml view
	}
	
	/**
	 * Handles user pressing edit button
	 */
	@FXML
	public void handleEditClick() {
		// Create new stage and window for edit view
		try {
			FXMLLoader fxml = new FXMLLoader(getClass().getResource(FXML_LOCATION + CUSTOM_LIST_SELECTION_EDIT_FXML));
			Parent root = (Parent) fxml.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			Scene scene = new Scene(root);
			scene.getRoot().requestFocus();
			stage.setTitle(TITLE);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setMaxHeight(420);
			stage.setMaxWidth(550);
			stage.setMinHeight(420);
			stage.setMinWidth(550);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Handles user entering key in text field, updates buttons accordingly
	 */
	@FXML
	public void handleTextFieldKeystroke() {
		TextQuestionListHandler handler = new TextQuestionListHandler(_textField.getText());
		
		// Change button visibility
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
	
	/**
	 * Handles key binding
	 * @param e : KeyEvent
	 */
	@FXML
	public void handleKeyPress(KeyEvent e) {
		if (e.getCode() == KeyCode.ESCAPE) {
			handleExitCick();
		} else if (e.getCode() == KeyCode.ENTER) {
			handleCreateClick();
		}
	}
	
	/**
	 * Handles user pressing create
	 */
	@FXML
	public void handleCreateClick() {
		Context.getInstance().setQuestionList(_textField.getText());
		
		changeWindow(TEACHER_INPUT_FXML, _createButton);// Change to MainWindow.fxml view
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_warningLabel.setVisible(false);
		_createButton.setDisable(true);
		
		_textField.textProperty().addListener(new ChangeListener<String>() {
			
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (_textField.getText().length() > MAX_VALUE)	{
					String s = _textField.getText().substring(0, MAX_VALUE);
					_textField.setText(s);
				}
			}
		});
		
		
	    Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	_textField.requestFocus();
	        }
	    });
	}
	
}
