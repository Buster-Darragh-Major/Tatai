package gui;

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
import javafx.stage.Stage;
import questionlist.TextQuestionListHandler;

public class CustomListEditViewController extends TataiController implements Initializable {

	/* FXML nodes */
	@FXML private FontAwesomeIconView _exitButton;
	@FXML private Button _editButton;
	@FXML private Button _deleteButton;
	@FXML private JFXListView<String> _listView;
	@FXML private JFXListView<String> _insideListView;
	@FXML private Label _label;
	@FXML private Label _warningLabel;
	
	/* Fields */
	private TextQuestionListHandler _handler;
	
	@FXML
	public void handleExitClick() {
		Stage stage = (Stage) _exitButton.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void handleEditButton() {
		if (_editButton.getText().equals("Back")) {
			_insideListView.setVisible(false);
			_label.setText("Custom Lists");
			_editButton.setText("Edit");
		} else {
			updateInsideList();
			
			_insideListView.setVisible(true);
			_label.setText(_listView.getSelectionModel().getSelectedItem());
			_editButton.setText("Back");
			_deleteButton.setDisable(true);
		}
	}
	
	@FXML
	public void handleDeleteButton() {
		if (_insideListView.isVisible()) {
			int lineNo = _insideListView.getSelectionModel().getSelectedIndex() + 1;
			_handler.delete(lineNo);
			updateInsideList();
			
			if (_handler.size() == 0) {
				_handler.delete();
				_insideListView.setVisible(false);
				_label.setText("Custom Lists");
				_editButton.setText("Edit");
				updateOutsideList();
			}
		} else {
			_handler = new TextQuestionListHandler(_listView.getSelectionModel().getSelectedItem());
			_handler.delete();
			updateOutsideList();
		}
		
		_deleteButton.setDisable(true);
	}
	
	@FXML
	public void handleListSelection() {
		if (_listView.getSelectionModel().getSelectedItem() == null) {
			_deleteButton.setDisable(true);
			_editButton.setDisable(true);
		} else {
			_deleteButton.setDisable(false);
			_editButton.setDisable(false);

		}
	}
	
	@FXML
	public void handleInnerListSelection() {
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
	
	private void updateOutsideList() {
		_label.setText("Custom Lists");
		
		_insideListView.setVisible(false);
		_warningLabel.setVisible(false);
		_editButton.setDisable(true);
		_deleteButton.setDisable(true);
		
		try {
			ArrayList<File> files = new ArrayList<File>(Arrays.asList(TextQuestionListHandler.LIST_DIRECTORY.listFiles()));
			ArrayList<String> items = new ArrayList<String>();
			for (File f : files) {
				String name = f.getName();
				if (name.contains(".txt")) {
					items.add(f.getName().replaceAll(".txt", ""));
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
	
	private void updateInsideList() {
		ArrayList<String> items = new ArrayList<String>();
		
		_handler = new TextQuestionListHandler(_listView.getSelectionModel().getSelectedItem());
		for (int i = 1; i <= _handler.size(); i++) {
			items.add(_handler.getLine(i));
		}
		_insideListView.setItems(FXCollections.observableArrayList(items));
	}
}
