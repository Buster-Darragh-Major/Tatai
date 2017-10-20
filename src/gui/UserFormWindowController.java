package gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import users.user.Student;
import users.user.Teacher;
import users.user.User;

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

		Task<Boolean> addTask = new Task<Boolean>() {

			@Override
			protected Boolean call() throws Exception {
				User u = null;
				if (_teacherCheckBox.isSelected()
						&& !Context.getInstance().currentGame().getClassRoom().containsUser(userName)) {
					u = new Teacher(firstName, lastName, userName);
					Context.getInstance().currentGame().getClassRoom().addTeacher((Teacher) u);
				} else if (!Context.getInstance().currentGame().getClassRoom().containsUser(userName)) {
					u = new Student(firstName, lastName, userName);
					Context.getInstance().currentGame().getClassRoom().addStudent((Student) u);
				} else {
					return false;
				}

				u.saveUser();
				return true;
			}
		};

		addTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, e -> {
			if (addTask.getValue()) {
				Stage stage = (Stage) _confirmButton.getScene().getWindow();
				changeWindow("UserWindow.fxml", stage);
			} else {
				showWarningDialog("Failed Creating User", "username already exists");
			}

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
		Stage stage = (Stage) _confirmButton.getScene().getWindow();
		changeWindow("UserWindow.fxml", stage);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_confirmButton.setDisable(true);
	}
}
