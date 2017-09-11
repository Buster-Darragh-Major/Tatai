package gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXHamburger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameWindowController extends TataiController implements Initializable{
	
	@FXML
	private Label _intLabel;
	@FXML
	private JFXHamburger _mainMenu;
	@FXML
	private Pane _pane;
	
	@FXML
	public void handleMainMenuClick() {
		Stage stage = (Stage) _mainMenu.getScene().getWindow(); // Get current stage
		changeWindow("MainWindow.fxml", stage); // Change to MainWindow.fxml view
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		int questionNo = Context.getInstance().currentGame().getQuestionNo();
		
		Context.getInstance().currentCreationModel().displayCreation(questionNo, _intLabel, _pane);
	}
}
