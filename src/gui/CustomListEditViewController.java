package gui;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import questionlist.TextQuestionListHandler;

public class CustomListEditViewController extends TataiController implements Initializable {

	@FXML private Button _exitButton;
	@FXML private Button _editButton;
	@FXML private Button _deleteButton;
	@FXML private JFXListView<String> _listView;
	@FXML private Label _label;
	@FXML private Label _warningLabel;
	
	@FXML
	public void handleExitClick() {
		Stage stage = (Stage) _exitButton.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void handleEditButton() {
		TextQuestionListHandler handler = new TextQuestionListHandler(_listView.getSelectionModel().getSelectedItem());
		for (int i = 1; i <= handler.size(); i++) {
			
		}
	}
	
	@FXML
	public void handleDeleteButton() {
		TextQuestionListHandler handler = new TextQuestionListHandler(_listView.getSelectionModel().getSelectedItem());
		handler.delete();
		
		update();
	}
	
	@FXML
	public void handleListSelection() {
		if (_listView.getSelectionModel().getSelectedItem() == null) {
			_editButton.setDisable(true);
			_deleteButton.setDisable(true);

		} else {
			_editButton.setDisable(false);
			_deleteButton.setDisable(false);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		update();
	}
	
	private void update() {
		_label.setText("Custom Lists");
		
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
}
