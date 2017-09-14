package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainWindowController extends TataiController {
	
	/* FXML Nodes */
	@FXML
	private Button _mainMenuPlay;
	@FXML
	private Button _mainMenuStats;
	@FXML
	private Button _mainMenuQuit;
	
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
	 * Allows the user to exit the application by clicking the quit button
	 */
	@FXML
	public void handleQuitClick() {
		System.exit(0);
	}
}
