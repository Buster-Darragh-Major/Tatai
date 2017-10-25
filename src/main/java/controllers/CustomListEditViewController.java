package main.java.controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.java.questionlist.TextQuestionListHandler;

/**
 * Of Note: this controller deals with concepts of inner and outer lists. The
 * outer list represents the list of custom question lists found in the list
 * folder. An inner list is a question list, and can be displayed in another
 * view.
 * 
 * @author Buster Darragh-Major
 */
public class CustomListEditViewController extends TataiController implements Initializable {

	/* FXML nodes */
	@FXML
	private FontAwesomeIconView _exitButton;
	@FXML
	private Button _editButton;
	@FXML
	private Button _deleteButton;
	@FXML
	private JFXListView<String> _listView;
	@FXML
	private JFXListView<String> _insideListView;
	@FXML
	private Label _label;
	@FXML
	private Label _warningLabel;

	/* Fields */
	private TextQuestionListHandler _handler;

	@FXML
	public void handleExitClick() {
		Stage stage = (Stage) _exitButton.getScene().getWindow();
		stage.close();
	}

	/**
	 * Handles user pressing edit button of program
	 */
	@FXML
	public void handleEditButton() {
		// Edit button text determines state of window, if labeled "back then user is in
		// an
		// inner lost, else is in an out list
		if (_editButton.getText().equals("Back")) { // Enter inside inner list edit, change button labeling
			_insideListView.setVisible(false);
			_label.setText("Custom Lists");
			_editButton.setText("Edit");
		} else { // Equivalent to entering into inner list
			updateInsideList(); // Set inner list to reflect new state of file

			_insideListView.setVisible(true);
			_label.setText(_listView.getSelectionModel().getSelectedItem());
			_editButton.setText("Back");
			_deleteButton.setDisable(true);
		}
	}

	/**
	 * Handles deleting of a list item
	 */
	@FXML
	public void handleDeleteButton() {
		if (_insideListView.isVisible()) { // In an inner list
			int lineNo = _insideListView.getSelectionModel().getSelectedIndex() + 1;
			_handler.delete(lineNo); // Delete custom list equation item
			updateInsideList();

			if (_handler.size() == 0) {
				_handler.delete();
				_insideListView.setVisible(false);
				_label.setText("Custom Lists");
				_editButton.setText("Edit");
				updateOutsideList();
			}
		} else { // In outer list
			_handler = new TextQuestionListHandler(_listView.getSelectionModel().getSelectedItem());
			_handler.delete(); // Delete the whoe file
			updateOutsideList();
		}

		_deleteButton.setDisable(true);
	}

	/**
	 * Handles user selecting an outer list item
	 */
	@FXML
	public void handleListSelection(MouseEvent e) {
		doubleClickCell(e);
		// Change button disabilities
		if (_listView.getSelectionModel().getSelectedItem() == null) {
			_deleteButton.setDisable(true);
			_editButton.setDisable(true);
		} else {
			_deleteButton.setDisable(false);
			_editButton.setDisable(false);

		}
	}

	/**
	 * Handles user selecting an inner list item
	 */
	@FXML
	public void handleInnerListSelection() {
		// Change button disabilities
		if (_insideListView.getSelectionModel().getSelectedItem() == null) {
			_deleteButton.setDisable(true);
		} else {
			_deleteButton.setDisable(false);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateOutsideList();
	}

	/**
	 * Updates the look of an outer list when called, reads through contents of
	 * folder and pushes to outer list view
	 */
	private void updateOutsideList() {
		_label.setText("Custom Lists");

		_insideListView.setVisible(false);
		_warningLabel.setVisible(false);
		_editButton.setDisable(true);
		_deleteButton.setDisable(true);

		try {
			ArrayList<File> files = new ArrayList<File>(
					Arrays.asList(TextQuestionListHandler.LIST_DIRECTORY.listFiles()));
			ArrayList<String> items = new ArrayList<String>();
			for (File f : files) {
				String name = f.getName();
				if (name.contains(TXT)) {
					items.add(f.getName().replaceAll(TXT, ""));
				}
			}

			if (items.isEmpty()) {
				_warningLabel.setVisible(true);
			}

			_listView.setItems(FXCollections.observableArrayList(items));
		} catch (NullPointerException e) {
			_warningLabel.setVisible(true);
		}
	}

	/**
	 * Handles key bindings for view
	 * 
	 * @param e
	 *            : KeyEvent
	 */
	@FXML
	public void handleKeyPress(KeyEvent e) {
		if (e.getCode() == KeyCode.ESCAPE) {
			handleExitClick();
		} else if (e.getCode() == KeyCode.ENTER) {
			handleEditButton();
		} else if (e.getCode() == KeyCode.DELETE) {
			handleDeleteButton();
		}
	}

	/**
	 * Updates the look of an outer list when called, reads through contents of file
	 * and pushes to inner list view
	 */
	private void updateInsideList() {
		ArrayList<String> items = new ArrayList<String>();

		_handler = new TextQuestionListHandler(_listView.getSelectionModel().getSelectedItem());
		for (int i = 1; i <= _handler.size(); i++) {
			items.add(_handler.getLine(i));
		}
		_insideListView.setItems(FXCollections.observableArrayList(items));
	}

	/**
	 * Handles double click of cell
	 * 
	 * @param e
	 *            : KeyEvent
	 */
	public void doubleClickCell(MouseEvent e) {
		if (e.getClickCount() == 2 && _listView.isVisible()) {
			handleEditButton();
		}
	}
}
