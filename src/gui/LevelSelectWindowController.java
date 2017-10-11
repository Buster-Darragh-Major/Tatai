package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tatai.creations.Level;

public class LevelSelectWindowController extends TataiController {

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
	
}
