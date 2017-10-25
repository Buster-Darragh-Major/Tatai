package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.java.users.classroom.ClassRoomException;
import main.java.users.user.Student;
import main.java.users.user.Teacher;
import main.java.users.user.User;
import main.java.users.user.UserException;

public class UserFormWindowController extends TataiController implements Initializable {
	/* MACROS */
	public final static int MAX_VALUE = 20;
	
	/* FXML Nodes */
	@FXML
	private JFXTextField _firstNameField;
	@FXML
	private JFXTextField _lastNameField;
	@FXML
	private JFXTextField _userNameField;
	@FXML
	private Button _confirmButton;
	@FXML
	private Button _backButton;
	@FXML
	private JFXCheckBox _teacherCheckBox;

	/**
	 * Handles user pressing confirm button
	 */
	@FXML
	public void handleConfirmClick() {
		String firstName = _firstNameField.getText().toString();
		String lastName = _lastNameField.getText().toString();
		String userName = _userNameField.getText().toString();

		// Create new user file in new thread
		Task<Void> addTask = new Task<Void>() {

			@Override
			protected Void call() throws UserException, ClassRoomException {
				User u = null;
				if (_teacherCheckBox.isSelected()) {
					u = new Teacher(firstName, lastName, userName);
					Context.getInstance().currentGame().getClassRoom().addTeacher((Teacher) u);
				} else {
					u = new Student(firstName, lastName, userName);
					Context.getInstance().currentGame().getClassRoom().addStudent((Student) u);
				}
				u.saveUser();
				return null;

			}
		};

		addTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, e -> {
			changeWindow(USER_FXML, _confirmButton);
		});

		addTask.setOnFailed(e -> {
			showWarningDialog("Failed Creating User", addTask.getException().getMessage());
		});

		startBackgroundThread(addTask);
	}
	
	/**
	 * Handles kay stroke in text field, will dynamically change button disabilities
	 */
	@FXML
	public void handleFormKeystroke() {
		if ((_firstNameField.getText().trim().equals("")) || 
				(_lastNameField.getText().trim().equals("")) || 
				(_userNameField.getText().trim().equals(""))) {
			_confirmButton.setDisable(true);
		} else {
			_confirmButton.setDisable(false);

		}
	}

	/**
	 * Handles user pressin back button
	 */
	@FXML
	public void handleBackClick() {
		changeWindow(USER_FXML, _confirmButton);
	}
	
	private class textDelimiter implements ChangeListener<String> {
		private JFXTextField textField;
		
		public textDelimiter(JFXTextField tf) {
			textField = tf;
		}
		
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			if (textField.getText().length() > MAX_VALUE)	{
				String s = textField.getText().substring(0, MAX_VALUE);
				textField.setText(s);
			}
		}
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_confirmButton.setDisable(true);
		
		_firstNameField.textProperty().addListener(new textDelimiter(_firstNameField));
		_lastNameField.textProperty().addListener(new textDelimiter(_lastNameField));
		_userNameField.textProperty().addListener(new textDelimiter(_userNameField));
		
	    Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	_firstNameField.requestFocus();
	        }
	    });
	}

	/**
	 * Handle key binds
	 * @param e The key event
	 */
	@FXML 
	public void handleKeyPress(KeyEvent e) {
		if (e.getCode() == KeyCode.ESCAPE) {
			handleBackClick();
		} else if (e.getCode() == KeyCode.ENTER) {
			if (!_confirmButton.isDisabled()) {
				handleConfirmClick();
			}
		}
	}
}
