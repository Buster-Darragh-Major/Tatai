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
import javafx.stage.Stage;

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
		Stage stage = (Stage) _mainMenuPlay.getScene().getWindow(); // Get current stage
		changeWindow("LevelSelectWindow.fxml", stage); // Change to LevelSelectWindow.fxml view
	}
	
	/**
	 * Handles user pressing statistics button
	 */
	@FXML
	public void handleStatsMenuClick() {
		Stage stage = (Stage) _mainMenuStats.getScene().getWindow(); // Get Current stage
		changeWindow("StatsWindow.fxml", stage);// Change to StatsWindow.fxml view
	}
	
	/**
	 * Handles pressing tutorial button
	 */
	@FXML
	public void handleTutorialClick() {
		Stage stage = (Stage) _mainMenuTutorial.getScene().getWindow(); // Get Current stage
		changeWindow("TutorialWindow.fxml", stage);// Change to TutorialWindow.fxml view
	}
	
	/**
	 * Handles pressing teachers button
	 */
	@FXML
	public void handleTechersClick() {
		Stage stage = (Stage) _teachersButton.getScene().getWindow(); // Get Current stage
		changeWindow("TeacherInputNamingWindow.fxml", stage);// Change to TeacherInputNamingWindow.fxml view
	}
	
	/**
	 * Allows the user to logout of the application by clicking the log out button
	 */
	@FXML
	public void handleQuitClick() {
		
		boolean confirmation = showWarningDialogConfirmation("Logging out", "Are you sure you want log out?");
		
		if (confirmation) {
			Context.getInstance().currentGame().logout();
			Stage stage = (Stage) _mainMenuQuit.getScene().getWindow();
			changeWindow("UserWindow.fxml", stage);
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
