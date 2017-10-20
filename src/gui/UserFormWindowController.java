package gui;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import users.classroom.ClassRoomException;
import users.user.Student;
import users.user.Teacher;
import users.user.User;
import users.user.UserException;

public class UserFormWindowController extends TataiController {
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
			Stage stage = (Stage) _confirmButton.getScene().getWindow();
			changeWindow("UserWindow.fxml", stage);
		});

		addTask.setOnFailed(e -> {
			showWarningDialog("Failed Creating User", addTask.getException().getMessage());
		});

		startBackgroundThread(addTask);
	}

	@FXML
	public void handleBackClick() {
		Stage stage = (Stage) _confirmButton.getScene().getWindow();
		changeWindow("UserWindow.fxml", stage);
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
			handleConfirmClick();
		}
	}
}
