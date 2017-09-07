package gui;

import com.jfoenix.controls.JFXHamburger;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class StatsWindowController extends TataiController {
	
	@FXML
	private JFXHamburger _statsViewBack;
	
	@FXML
	public void handleBackStatsClick() {
		Stage stage = (Stage) _statsViewBack.getScene().getWindow(); // Get current stage
		changeWindow("MainWindow.fxml", stage); // Change to MainWindow.fxml view
	}

}
