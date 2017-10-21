package gui;

import java.net.URL;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StatsHelpWindowController extends TataiController implements Initializable {
	
	@FXML
	private FontAwesomeIconView _backButton;
	
	@FXML
	public void handleBackClick() {
		Stage stage = (Stage) _backButton.getScene().getWindow();
		changeWindow("StatsWindow.fxml", stage);
	}
	
	@FXML
	public void handleKeyPress(KeyEvent e) {
		System.out.println("ff");
		if (e.getCode() == KeyCode.ESCAPE) {
			System.out.println("esc");
			handleBackClick();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
