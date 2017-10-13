package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import tatai.creations.Level;

public class LevelSelectWindowController extends TataiController implements Initializable{

	/* FXML Nodes */
	@FXML 
	private Button _level1;
	@FXML
	private Button _level2;
	@FXML
	private Button _practice;
	@FXML
	private Button _menuButton;
	
	/**
	 * Handles user pressing level 1 select
	 */
	@FXML
	public void handleLevel1Click() {
		Context.getInstance().currentGame().setLevel(Level.Level1);
		
		Stage stage = (Stage) _level1.getScene().getWindow(); // Get current stage
		changeWindow("LevelSelectConfirmationWindow.fxml", stage); // Change to GameWindow.fxml view
	}
	
	/**
	 * Handles user pressing level 2 select
	 */
	@FXML
	public void handleLevel2Click() {
		Context.getInstance().currentGame().setLevel(Level.Level2);
		
		Stage stage = (Stage) _level2.getScene().getWindow(); // Get current stage
		changeWindow("LevelSelectConfirmationWindow.fxml", stage); // Change to GameWindow.fxml view
	}
	
	
	@FXML
	public void handlePracticeClick() {
		Stage stage = (Stage) _practice.getScene().getWindow(); // Get current stage
		changeWindow("PracticeWindow.fxml", stage); // Change to GameWindow.fxml view
	}
	
	/**
	 * Handles user pressing main menu button
	 */
	@FXML
	public void handleMenuButtonClick() {
		Stage stage = (Stage) _menuButton.getScene().getWindow(); // Get current stage
		changeWindow("MainWindow.fxml", stage); // Change to MainWindow.fxml
	}
	
	/**
	 * Handle key binds
	 */
	@FXML
	public void handleKeyPress(KeyEvent e) {
		if (e.getCode() == KeyCode.ESCAPE) {
			handleMenuButtonClick();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	    Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	    		_level1.requestFocus();
	        }
	    });
	}
	
}
