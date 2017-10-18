package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CustomListSelectionWindowController extends TataiController implements Initializable {

	/* FXML Nodes */
	@FXML private Button _exitButton;
	@FXML private Button _playButton;
	@FXML private JFXListView<String> _listView;
	
	/* Fields */
	private ArrayList<String> _list;

	@FXML
	public void handleExitClick() {
		Stage stage = (Stage) _exitButton.getScene().getWindow(); // Get current stage
		changeWindow("LevelSelectWindow.fxml", stage); // Change to LevelSelectWindow.fxml
	}
	
	@FXML
	public void handlePlayClick() {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
}
