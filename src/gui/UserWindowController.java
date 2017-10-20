package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

public class UserWindowController extends TataiController implements Initializable {
	@FXML
	Button _continueButton;
	@FXML
	Button _addButton;
	@FXML
	Button _deleteButton;
	@FXML
	Button _quitButton;
	@FXML
	ListView<String> _userList1;
	@FXML
	ListView<String> _userList2;

	@FXML
	public void handleContinueClick() {

	}

	/**
	 * Add a user
	 */
	@FXML
	public void handleAddClick() {
		Stage stage = (Stage) _addButton.getScene().getWindow();
		changeWindow("UserFormWindow.fxml", stage);
	}
	
	/**
	 * DELETE a user
	 */
	@FXML
	public void handleDeleteClick() {

		Task<String> deletionTask = new Task<String>() {

			@Override
			protected String call() throws Exception {
				String userName = null;
				if (_userList2.getSelectionModel().isEmpty()) {
					userName = _userList1.getSelectionModel().getSelectedItem();	
					Context.getInstance().currentGame().getClassRoom().removeStudent(userName);
				} else if (_userList1.getSelectionModel().isEmpty()) {
					userName = _userList2.getSelectionModel().getSelectedItem();
					Context.getInstance().currentGame().getClassRoom().removeTeacher(userName);
				}
				return userName;
			}
		};
		
		deletionTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, e -> {
			String userName = deletionTask.getValue();
			if (_userList2.getSelectionModel().isEmpty()) {
				_userList1.getItems().remove(userName);
			} else if (_userList1.getSelectionModel().isEmpty()) {
				_userList2.getItems().remove(userName);
			}
		});
		
		startBackgroundThread(deletionTask);
	}

	/**
	 * Quits the program
	 */
	@FXML
	public void handleQuitClick() {
		System.exit(0);
	}

	/**
	 * Init the controller
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> teachers = FXCollections
				.observableArrayList(Context.getInstance().currentGame().getClassRoom().listTeachers());
		ObservableList<String> students = FXCollections
				.observableArrayList(Context.getInstance().currentGame().getClassRoom().listStudents());

		_userList1.setItems(students);
		_userList2.setItems(teachers);

		_userList1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		_userList2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		// Allows selection of only one list
		_userList1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (_userList2.isFocused()) {
					_userList2.getSelectionModel().clearSelection();
				}
			}

		});

		// Allows selection of only one list
		_userList2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (_userList1.isFocused()) {
					_userList1.getSelectionModel().clearSelection();
				}
			}

		});
	}
}
