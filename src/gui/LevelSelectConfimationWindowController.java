package gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXCheckBox;

import game.Level;
import game.TataiGameReverse;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LevelSelectConfimationWindowController extends TataiController implements Initializable {

	/* FXML Nodes */
	@FXML private Button _start;
	@FXML private Button _back;
	@FXML private Label _levelHeader;
	@FXML private Label _levelDescriptor;
	@FXML private JFXCheckBox _checkBox;
	
	/**
	 * Handles user pressing start button
	 */
	@FXML
	public void handleStartClick() {
		
		if (_checkBox.isSelected()) {
			
		} else {
			// Set level of TataiCreationModel to be level defined in current game object
			// also stored in s Context object
			Context.getInstance().currentGame().setLevel(
					Context.getInstance().currentGame().currentLevel());
			
			// Populate TataiCreationModel object in singleton
			Context.getInstance().currentGame().startGame();
			
			Stage stage = (Stage) _start.getScene().getWindow(); // Get current stage
			changeWindow("GameWindow.fxml", stage); // Change to GameWindow.fxml view
		}
	}
	
	/**
	 * Handles clicking of check box by the user, changes label that is displayed and game type.
	 */
	@FXML
	public void handleCheckBoxClick() {
		if (_checkBox.isSelected()) {
			TataiGameReverse reverseGame = new TataiGameReverse();

			if (Context.getInstance().currentGame().currentLevel() == Level.Level1) {
				reverseGame.setLevel(Level.Level1);
			} else {
				reverseGame.setLevel(Level.Level2);
			}
			
			Context.getInstance().setGameType(reverseGame);
			
			_levelDescriptor.setText(Context.getInstance().currentGame().getLevelDescription());
		} else {
			Context.getInstance().setGameToEquation();
			
			_levelDescriptor.setText(Context.getInstance().currentGame().getLevelDescription());
		}
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
		// Set header and description labels to be that representing the current game
		// objects set difficulty.
		_levelHeader.setText(Context.getInstance().currentGame().getLevelHeader());
		_levelDescriptor.setText(Context.getInstance().currentGame().getLevelDescription());
		
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
