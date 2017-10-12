package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LevelSelectConfimationWindowController extends TataiController implements Initializable {

	/* FXML Nodes */
	@FXML
	private Button _start;
	@FXML
	private Button _back;
	@FXML
	private Label _levelHeader;
	@FXML
	private Label _levelDescriptor;
	
	/**
	 * Handles user pressing start button
	 */
	@FXML
	public void handleStartClick() {
		
		// Set level of TataiCreationModel to be level defined in current game object
		// also stored in s Context object
		Context.getInstance().currentGame().setLevel(
				Context.getInstance().currentGame().currentLevel());
		
		// Populate TataiCreationModel object in singleton
		Context.getInstance().currentGame().startGame();
		
		Stage stage = (Stage) _start.getScene().getWindow(); // Get current stage
		changeWindow("GameWindow.fxml", stage); // Change to GameWindow.fxml view
	}
	
	/**
	 * Handles user pressing back button
	 */
	@FXML
	public void handleBackClick() {
		Stage stage = (Stage) _back.getScene().getWindow(); // Get current stage
		changeWindow("LevelSelectWindow.fxml", stage); // Change to LevelSelectWindow.fxml view
	}

	/**
	 * Initializes the window state
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Set header and description labels to be that representing the current game
		// objects set difficulty.
		_levelHeader.setText(Context.getInstance().currentGame().getLevelHeader());
		_levelDescriptor.setText(Context.getInstance().currentGame().getLevelDescription());
	}
	
}
