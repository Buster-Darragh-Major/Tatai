package gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXHamburger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
		// This is a bit messy isnt it
		// TODO: Clean it up
		int questionNo = Context.getInstance().currentGame().getQuestionNo();
		String bgColor = Context.getInstance().currentCreationModel().getCreation(questionNo).backgroundColor().toString();
		bgColor = bgColor.substring(2, bgColor.length() - 2);
		
		_intLabel.setAlignment(Pos.CENTER);
		_intLabel.setText(Context.getInstance().currentCreationModel().getCreation(questionNo).label() + "");
		_intLabel.setTextFill(Context.getInstance().currentCreationModel().getCreation(questionNo).textColor());
		_pane.setStyle("-fx-background-color: #" + bgColor);
	}
}
