package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

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

	@FXML
	public void handleConfirmClick() {
		String firstName = _firstNameField.getText().toString();
		String lastName = _lastNameField.getText().toString();
		String userName = _userNameField.getText().toString();

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

	@FXML
	public void handleBackClick() {
		changeWindow(USER_FXML, _confirmButton);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_confirmButton.setDisable(true);
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
