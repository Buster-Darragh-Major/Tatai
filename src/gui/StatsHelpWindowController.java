package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class StatsHelpWindowController extends TataiController{
	
	@FXML
	private FontAwesomeIconView _backButton;
	
	@FXML
	public void handleBackClick() {
		Stage stage = (Stage) _backButton.getScene().getWindow();
		changeWindow("StatsWindow.fxml", stage);
	}

}
