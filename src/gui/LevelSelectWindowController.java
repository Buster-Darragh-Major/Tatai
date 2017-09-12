package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tatai.creations.Level;

public class LevelSelectWindowController extends TataiController {

	@FXML 
	private Button _level1;
	@FXML
	private Button _level2;
	
	@FXML
	public void handleLevel1Click() {
		Context.getInstance().newGame();
		Context.getInstance().currentGame().setLevel(Level.Level1);
		
		Stage stage = (Stage) _level1.getScene().getWindow(); // Get current stage
		changeWindow("LevelSelectConfirmationWindow.fxml", stage); // Change to GameWindow.fxml view
	}
	
	@FXML
	public void handleLevel2Click() {
		Context.getInstance().newGame();
		Context.getInstance().currentGame().setLevel(Level.Level2);
		
		Stage stage = (Stage) _level2.getScene().getWindow(); // Get current stage
		changeWindow("LevelSelectConfirmationWindow.fxml", stage); // Change to GameWindow.fxml view
	}
	
}
