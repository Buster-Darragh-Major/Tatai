package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class TutorialWindowController  extends TataiController implements Initializable {

	/* FXML Nodes */
	@FXML
	private Button _nextButton;
	@FXML
	private Label _numberLabel;
	@FXML
	private Circle _progressIcon1;
	@FXML
	private Circle _progressIcon2;
	@FXML
	private Circle _progressIcon3;
	@FXML
	private Circle _progressIcon4;
	@FXML
	private Circle _progressIcon5;
	@FXML
	private Circle _progressIcon6;
	
	/* Fields */
	private int _page = 1;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_numberLabel.setText(_page + ".");
	}

}
