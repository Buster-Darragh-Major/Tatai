package main.java.controllers;

import java.net.URL;
import java.util.Collections;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import main.java.users.user.User;

public class UserWindowController extends TataiController implements Initializable {
	
	/* FXML Nodes */
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

	/**
	 * goto next main window
	 */
	@FXML
	public void handleContinueClick() {
		String username = getSelection();
		ListView<String> l = getSelectedList();
		User user = null;

		if (username != null) {
			if (l.equals(_userList1)) {
				user = Context.getInstance().currentGame().getClassRoom().getStudentByUsername(username);
			} else if (l.equals(_userList2)) {
				user = Context.getInstance().currentGame().getClassRoom().getTeacherByUsername(username);
			}

			if (user != null) {
				Context.getInstance().setUser(user);
				Context.getInstance().currentGame().setCurrentUser(user);
				
				changeWindow(MAIN_FXML, _continueButton);
			}
		} else {
			// Tell user no user is selected
			showWarningDialog("No User selected", "Please select a user");
		}
	}

	/**
	 * Determines whether to enable login buttons
	 */
	@FXML
	public void handleListClick(MouseEvent e) {
		doubleClickCell(e);
		if ((_userList1.getSelectionModel().getSelectedItem() == null)
				&& (_userList2.getSelectionModel().getSelectedItem() == null)) {
			_continueButton.setDisable(true);
		} else {
			_continueButton.setDisable(false);
		}
		if (e.getSource().equals(_userList1)) {
			clearListSelection(_userList2);
			_userList1.requestFocus();
		} else {
			clearListSelection(_userList1);
			_userList2.requestFocus();
		}
	}
	
	/**
	 * Handles double click of cell
	 * @param e : KeyEvent
	 */
	public void doubleClickCell(MouseEvent e) {
        if (e.getClickCount() == 2) {
            handleContinueClick();
         }
	}

	/**
	 * Add a user
	 */
	@FXML
	public void handleAddClick() {
		changeWindow(USER_FORM_FXML, _addButton);
	}

	/**
	 * DELETE a user
	 */
	@FXML
	public void handleDeleteClick() {
		_continueButton.setDisable(true);

		String userName = getSelection();

		boolean confirmation = false;
		if (userName != null) {
			confirmation = showWarningDialogConfirmation("Deletion Confirmation",
					"Are you sure you want to delete: " + userName);
		}

		// Create new thread for deletion task
		if (confirmation) {
			Task<Void> deletionTask = new Task<Void>() {

				@Override
				protected Void call() throws Exception {
					ListView<String> l = getSelectedList();

					if (l.equals(_userList1)) {
						Context.getInstance().currentGame().getClassRoom().removeStudent(userName);
					} else if (l.equals(_userList2)) {
						Context.getInstance().currentGame().getClassRoom().removeTeacher(userName);
					}

					return null;
				}
			};

			deletionTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, e -> {
				if (_userList2.getSelectionModel().isEmpty()) {
					_userList1.getItems().remove(userName);
				} else if (_userList1.getSelectionModel().isEmpty()) {
					_userList2.getItems().remove(userName);
				}
				if (_userList1.getSelectionModel().getSelectedIndex() != -1) {
					_userList1.getSelectionModel().clearSelection();
				} else if (_userList2.getSelectionModel().getSelectedIndex() != 1) {
					_userList2.getSelectionModel().clearSelection();
				}
			});

			startBackgroundThread(deletionTask);
		}
	}

	/**
	 * Quits the program
	 */
	@FXML
	public void handleQuitClick() {
		System.exit(0);
	}

	/**
	 * Gets the currently selected user
	 * 
	 * @return the currently selected user
	 */
	private String getSelection() {
		String userName = null;
		ListView<String> l = getSelectedList();
		if (l.equals(_userList1)) {
			userName = l.getSelectionModel().getSelectedItem();
		} else if (l.equals(_userList2)) {
			userName = l.getSelectionModel().getSelectedItem();
		}
		return userName;
	}

	private ListView<String> getSelectedList() {
		ListView<String> l = null;
		if (!_userList1.getSelectionModel().isEmpty()) {
			l = _userList1;
		} else if (!_userList2.getSelectionModel().isEmpty()) {
			l = _userList2;
		}
		return l;
	}

	private void clearListSelection(ListView<String> l) {
		if (l.isFocused()) {
			l.getSelectionModel().clearSelection();
		}
	}

	/**
	 * Handle key binds
	 * 
	 * @param e
	 *            The key event
	 */
	@FXML
	public void handleKeyPress(KeyEvent e) {
		if (e.getCode() == KeyCode.ESCAPE) {
			handleQuitClick();
		} else if (e.getCode() == KeyCode.ENTER) {
			if (!_continueButton.isDisabled()) {
				handleContinueClick();
			}
		// This seems a bit weird but the only way to fix issues with list selection. 
		} else if (e.getCode() == KeyCode.RIGHT) {
			clearListSelection(_userList1);
		} else if (e.getCode() == KeyCode.LEFT) {
			clearListSelection(_userList2);
		}
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
		
		Collections.sort(teachers);
		Collections.sort(students);
		
		_userList1.setItems(students);
		_userList2.setItems(teachers);

		_userList1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		_userList2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		_continueButton.setDisable(true);

		// Allows selection of only one list
		_userList1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				clearListSelection(_userList2);
			}

		});

		// Allows selection of only one list
		_userList2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				clearListSelection(_userList1);
			}
		});
	}
}
