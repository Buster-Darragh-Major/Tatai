package gui;

import com.jfoenix.controls.JFXHamburger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LevelSelectWindowController extends TataiController {

	@FXML
	private JFXHamburger _mainMenu;
	@FXML 
	private Button _level1;
	@FXML
	private Button _level2;
	
	@FXML
	public void handleMainMenuCLick() {
		Stage stage = (Stage) _mainMenu.getScene().getWindow(); // Get current stage
		changeWindow("MainWindow.fxml", stage); // Change to MainWindow.fxml view
	}
	
	@FXML
	public void handleLevel1Click() {
		
	}
	
	@FXML
	public void handleLevel2Click() {
		
	}
	
}
