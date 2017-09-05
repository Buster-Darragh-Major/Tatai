package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StatsWindowController extends TataiController {
	
	@FXML
	private Button _statsViewBack;
	
	@FXML
	public void handleBackStatsClick() {
		Stage stage = (Stage) _statsViewBack.getScene().getWindow(); // Get current stage
		changeWindow("MainWindow.fxml", stage); // Change to MainWindow.fxml view
	}

}
