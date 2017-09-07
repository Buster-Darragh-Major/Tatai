package gui;

import com.jfoenix.controls.JFXHamburger;

import creations.tatai.TataiCreationBuilder.Level;
import creations.tatai.TataiCreationModel;
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
	
	private TataiCreationModel _model;
	
	@FXML
	public void handleMainMenuCLick() {
		Stage stage = (Stage) _mainMenu.getScene().getWindow(); // Get current stage
		changeWindow("MainWindow.fxml", stage); // Change to MainWindow.fxml view
	}
	
	@FXML
	public void handleLevel1Click() {
		Context.getInstance().newGame();
		Context.getInstance().currentGame().setLevel(Level.Level1);
		
		_model = new TataiCreationModel();
		_model.setLevel(Level.Level1);
		_model.populateModel();
		
		Stage stage = (Stage) _level1.getScene().getWindow(); // Get current stage
		changeWindow("LevelSelectConfirmationWindow.fxml", stage); // Change to GameWindow.fxml view
	}
	
	@FXML
	public void handleLevel2Click() {
		Context.getInstance().newGame();
		Context.getInstance().currentGame().setLevel(Level.Level2);
		
		_model = new TataiCreationModel();
		_model.setLevel(Level.Level2);
		_model.populateModel();
		
		Stage stage = (Stage) _level2.getScene().getWindow(); // Get current stage
		changeWindow("LevelSelectConfirmationWindow.fxml", stage); // Change to GameWindow.fxml view
	}
	
}
