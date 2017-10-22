package main.java.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXCheckBox;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import main.java.game.Level;
import main.java.game.TataiGameReverse;
import main.java.users.user.User;

public class LevelSelectConfimationWindowController extends TataiController implements Initializable {
	
	/* FIELDS */
	private User _user;
	
	/* FXML Nodes */
	@FXML private Button _start;
	@FXML private Button _back;
	@FXML private Label _levelHeader;
	@FXML private Label _levelDescriptor;
	@FXML private JFXCheckBox _checkBox;
	
	public LevelSelectConfimationWindowController() {
		_user = _game.getCurrentUser();
	}
	
	/**
	 * Handles user pressing start button
	 */
	@FXML
	public void handleStartClick() {
		// Populate TataiCreationModel object in singleton
		_game.startGame();
		
		Stage stage = (Stage) _start.getScene().getWindow(); // Get current stage
		if (_checkBox.isSelected()) {
			changeWindow("ReverseGamemodeWindow.fxml", stage); // Change to ReverseGamemodeWindow.fxml view
		} else {
			changeWindow("GameWindow.fxml", stage); // Change to GameWindow.fxml view
		}
	}
	
	/**
	 * Handles clicking of check box by the user, changes label that is displayed and game type.
	 */
	@FXML
	public void handleCheckBoxClick() {
		if (_checkBox.isSelected()) { // Set game type to reverse game and set to current level
			TataiGameReverse reverseGame = new TataiGameReverse();
			
			Level level;
			if (_game.currentLevel() == Level.LEVEL1 || _game.currentLevel() == Level.LEVEL1_REVERSE) {
				level = Level.LEVEL1_REVERSE;
			} else {
				level = Level.LEVEL2_REVERSE;
			}
			reverseGame.setLevel(level);
			reverseGame.setCurrentUser(Context.getInstance().getUser());
			Context.getInstance().setGameType(reverseGame);
			_game = Context.getInstance().currentGame();
		} else {
			Context.getInstance().setGameToEquation(); 
		}
		
		_levelDescriptor.setText(Context.getInstance().currentGame().getLevelDescription());

	}
	
	/**
	 * Handles user pressing back button
	 */
	@FXML
	public void handleBackClick() {
		Stage stage = (Stage) _back.getScene().getWindow(); // Get current stage
		changeWindow("LevelSelectWindow.fxml", stage); // Change to LevelSelectWindow.fxml view
	}

	/**
	 * Initializes the window state
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Context.getInstance().setGameToEquation();
		_game = Context.getInstance().currentGame();
		
		// Set header and description labels to be that representing the current game
		// objects set difficulty.
		_levelHeader.setText(_game.getLevelHeader());
		_levelDescriptor.setText(_game.getLevelDescription());
		
		if (_game.currentLevel() == Level.LEVEL2 && !_user.isUnlocked(Level.LEVEL2_REVERSE)) {
			_checkBox.setDisable(true);
		} else {
			_checkBox.setDisable(false);
		}
		
	    Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	    		_start.requestFocus();
	        }
	    });
	}
	
	@FXML
	public void handleKeyPress(KeyEvent e) {
		if (e.getCode() == KeyCode.ESCAPE) {
			handleBackClick();
		}
	}
	
}
