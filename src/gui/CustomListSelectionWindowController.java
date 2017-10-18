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
import javafx.stage.Stage;
import res.questionlist.TextQuestionListHandler;

public class CustomListSelectionWindowController extends TataiController implements Initializable {

	/* FXML Nodes */
	@FXML private Button _exitButton;
	@FXML private Button _playButton;
	@FXML private JFXListView<String> _listView;

	@FXML
	public void handleExitClick() {
		Stage stage = (Stage) _exitButton.getScene().getWindow(); // Get current stage
		changeWindow("LevelSelectWindow.fxml", stage); // Change to LevelSelectWindow.fxml
	}
	
	@FXML
	public void handlePlayClick() {
		
	}
	
	@FXML
	public void handleListSelection() {
		if (_listView.getSelectionModel().getSelectedItem() == null) {
			_playButton.setDisable(true);
		} else {
			_playButton.setDisable(false);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<File> files = new ArrayList<File>(Arrays.asList(TextQuestionListHandler.LIST_DIRECTORY.listFiles()));
		ArrayList<String> items = new ArrayList<String>();
		for (File f : files) {
			String name = f.getName();
			if (name.contains(".txt")) {
				items.add(f.getName().replaceAll(".txt", ""));
			}
		}
		
		_listView.setItems(FXCollections.observableArrayList(items));
		
		_playButton.setDisable(true);
	}
}
