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
import main.java.game.TataiGameCustomList;
import main.java.questionlist.TextQuestionListHandler;

public class CustomListSelectionWindowController extends TataiController implements Initializable {

	/* FXML Nodes */
	@FXML private FontAwesomeIconView _exitButton;
	@FXML private Button _playButton;
	@FXML private JFXListView<String> _listView;
	@FXML private Label _warningLabel;

	/**
	 * Handles user pressing exit button
	 */
	@FXML
	public void handleExitClick() {
		changeWindow(LEVEL_SELECT_FXML, _exitButton); // Change to LevelSelectWindow.fxml
	}
	
	/**
	 * Handles user pressing play button
	 */
	@FXML
	public void handlePlayClick() {
		// Set game type and list to read off for Context singleton
		Context.getInstance().setGameType(
				new TataiGameCustomList(
				new TextQuestionListHandler(_listView.getSelectionModel().getSelectedItem())));
		Context.getInstance().currentGame().populateModel();
		
		changeWindow(GAME_FXML, _playButton); // Change to GameokWindow.fxml
	}
	
	/**
	 * Handles user selecting list item
	 */
	@FXML
	public void handleListSelection() {
		// Change button disabilities
		if (_listView.getSelectionModel().getSelectedItem() == null) {
			_playButton.setDisable(true);
		} else {
			_playButton.setDisable(false);
		}
	}
	
	/**
	 * Handles key bindings for window 
	 * @param e : KeyEvent
	 */
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
				if (name.contains(TXT)) {
					items.add(f.getName().replaceAll(TXT, ""));
				}
			}
			
			_listView.setItems(FXCollections.observableArrayList(items));
		} catch (NullPointerException e) {
			_warningLabel.setVisible(true);
		}
	}
}
