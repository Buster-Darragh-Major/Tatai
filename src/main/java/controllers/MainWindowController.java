package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainWindowController extends TataiController  implements Initializable{
	
	/* FXML Nodes */
	@FXML private Button _mainMenuPlay;
	@FXML private Button _mainMenuStats;
	@FXML private Button _mainMenuQuit;
	@FXML private FontAwesomeIconView _mainMenuTutorial;
	@FXML private FontAwesomeIconView _teachersButton;
	@FXML private Label _userLabel;
	
	/**
	 * Handles user pressing play button
	 */
	@FXML
	public void handlePlayMenuClick() {
		changeWindow(LEVEL_SELECT_FXML, _mainMenuPlay); // Change to LevelSelectWindow.fxml view
	}
	
	/**
	 * Handles user pressing statistics button
	 */
	@FXML
	public void handleStatsMenuClick() {
		changeWindow(STATS_FXML, _mainMenuStats);// Change to StatsWindow.fxml view
	}
	
	/**
	 * Handles pressing tutorial button
	 */
	@FXML
	public void handleTutorialClick() {
		changeWindow(TUTORIAL_FXML, _mainMenuTutorial);// Change to TutorialWindow.fxml view
	}
	
	/**
	 * Handles pressing teachers button
	 */
	@FXML
	public void handleTechersClick() {
		changeWindow(TEACHER_INPUT_NAMING_FXML, _teachersButton);// Change to TeacherInputNamingWindow.fxml view
	}
	
	/**
	 * Allows the user to logout of the application by clicking the log out button
	 */
	@FXML
	public void handleQuitClick() {
		
		boolean confirmation = showWarningDialogConfirmation("Logging out", "Are you sure you want log out?");
		
		if (confirmation) {
			Context.getInstance().currentGame().logout();
			changeWindow(USER_FXML, _mainMenuQuit);
		}
	}
	
	/**
	 * Handle key binds
	 * @param e The key event
	 */
	@FXML 
	public void handleKeyPress(KeyEvent e) {
		if (e.getCode() == KeyCode.ESCAPE) {
			handleQuitClick();
		} else if (e.getCode() == KeyCode.F1) {
			handleTutorialClick();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	    Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	    		_mainMenuPlay.requestFocus();
	        }
	    });
	    
	    if (Context.getInstance().currentGame().getCurrentUser().hasWritingPrivileges()) {
	    	_teachersButton.setVisible(true);
	    } else {
	    	_teachersButton.setVisible(false);
	    }
	    
	    _userLabel.setText(Context.getInstance().currentGame().getCurrentUser().name());
	}
}
