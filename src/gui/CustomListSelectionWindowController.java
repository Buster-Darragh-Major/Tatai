package gui;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import game.TataiGameCustomList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import questionlist.TextQuestionListHandler;

public class CustomListSelectionWindowController extends TataiController implements Initializable {

	/* FXML Nodes */
	@FXML private FontAwesomeIconView _exitButton;
	@FXML private Button _playButton;
	@FXML private JFXListView<String> _listView;
	@FXML private Label _warningLabel;

	@FXML
	public void handleExitClick() {
		Stage stage = (Stage) _exitButton.getScene().getWindow(); // Get current stage
		changeWindow("LevelSelectWindow.fxml", stage); // Change to LevelSelectWindow.fxml
	}
	
	@FXML
	public void handlePlayClick() {
		// Set game type and list to read off for Context singleton
		Context.getInstance().setGameType(
				new TataiGameCustomList(
				new TextQuestionListHandler(_listView.getSelectionModel().getSelectedItem())));
		Context.getInstance().currentGame().populateModel();
		
		Stage stage = (Stage) _playButton.getScene().getWindow(); // Get current stage
		changeWindow("GameWindow.fxml", stage); // Change to GameokWindow.fxml
	}
	
	@FXML
	public void handleListSelection() {
		if (_listView.getSelectionModel().getSelectedItem() == null) {
			_playButton.setDisable(true);
		} else {
			_playButton.setDisable(false);
		}
	}
	
	@FXML
	public void handleKeyPress(KeyEvent e) {
		if (e.getCode() == KeyCode.ESCAPE) {
			handleExitClick();
		} else if (e.getCode() == KeyCode.ENTER) {
			handlePlayClick();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_playButton.setDisable(true);
		_warningLabel.setVisible(false);
		
		try {
			ArrayList<File> files = new ArrayList<File>(Arrays.asList(TextQuestionListHandler.LIST_DIRECTORY.listFiles()));
			ArrayList<String> items = new ArrayList<String>();
			for (File f : files) {
				String name = f.getName();
				if (name.contains(".txt")) {
					items.add(f.getName().replaceAll(".txt", ""));
				}
			}
			
			_listView.setItems(FXCollections.observableArrayList(items));
		} catch (NullPointerException e) {
			_warningLabel.setVisible(true);
		}
	}
}
