package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainWindowController {
	
	// Main Menu Buttons
	@FXML
	private Button _mainMenuPlay;
	@FXML
	private Button _mainMenuStats;
	@FXML
	private Button _mainMenuQuit;
	
	@FXML
	public void handleStatsMenuPlayClick() {
		Parent root = null;
		try {
			// Load root .fxml file for stats view
			root = FXMLLoader.load(getClass().getResource("StatsWindow.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Get current stage
		Stage stage = (Stage) _mainMenuStats.getScene().getWindow();
		
		Scene scene = new Scene(root); // Create new scene based off stats view root
		stage.setScene(scene); // Set current stage to show new stats view scene
		stage.show();
	}
}
