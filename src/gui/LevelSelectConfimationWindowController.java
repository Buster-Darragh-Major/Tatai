package gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXHamburger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LevelSelectConfimationWindowController extends TataiController implements Initializable {

	@FXML
	private Button _start;
	@FXML
	private Button _back;
	@FXML
	private JFXHamburger _mainMenu;
	@FXML
	private Label _levelHeader;
	@FXML
	private Label _levelDescriptor;
	
	@FXML
	public void handleStartClick() {
		Stage stage = (Stage) _start.getScene().getWindow(); // Get current stage
		changeWindow("GameWindow.fxml", stage); // Change to GameWindow.fxml view
	}
	
	@FXML
	public void handleBackClick() {
		Stage stage = (Stage) _back.getScene().getWindow(); // Get current stage
		changeWindow("LevelSelectWindow.fxml", stage); // Change to LevelSelectWindow.fxml view
	}
	
	@FXML
	public void handleMainMenuClick() {
		Stage stage = (Stage) _mainMenu.getScene().getWindow(); // Get current stage
		changeWindow("MainWindow.fxml", stage); // Change to LevelSelectWindow.fxml view
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_levelHeader.setText(Context.getInstance().currentGame().getLevelHeader());
		_levelDescriptor.setText(Context.getInstance().currentGame().getLevelDescription());
	}
	
	
}
