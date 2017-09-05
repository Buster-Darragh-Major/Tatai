package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainWindowController extends TataiController {
	
	// Main Menu Buttons
	@FXML
	private Button _mainMenuPlay;
	@FXML
	private Button _mainMenuStats;
	@FXML
	private Button _mainMenuQuit;
	
	@FXML
	public void handlePlayMenuClick() {
		Stage stage = (Stage) _mainMenuPlay.getScene().getWindow(); // Get current stage
		changeWindow("GameWindow.fxml", stage); // Change to GameWindow.fxml view
	}
	
	@FXML
	public void handleStatsMenuClick() {
		Stage stage = (Stage) _mainMenuStats.getScene().getWindow(); // Get Current stage
		changeWindow("StatsWindow.fxml", stage);// Change to StatsWindow.fxml view
	}
}
