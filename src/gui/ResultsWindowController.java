package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ResultsWindowController extends TataiController implements Initializable {

	@FXML
	private Label _scoreLabel;

	@SuppressWarnings("static-access")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_scoreLabel.setText("You scored " + Context.getInstance().currentGame().questionsCorrect() + 
				"/" + Context.getInstance().currentGame().TOTAL_NUMBER_OF_QUESTIONS);
	}
	
}
