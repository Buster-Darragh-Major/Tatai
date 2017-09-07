package gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jfoenix.controls.JFXHamburger;

import creations.tatai.TataiCreation;
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
		_model = new TataiCreationModel();
		_model.setLevel(Level.Level1);
		_model.populateModel();
	}
	
	@FXML
	public void handleLevel2Click() {
		_model = new TataiCreationModel();
		_model.setLevel(Level.Level2);
		_model.populateModel();
	}
	
}
