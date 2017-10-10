package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
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
	private List<Circle> _progress = new ArrayList<Circle>(Arrays.asList(
			_progressIcon1, 
			_progressIcon2, 
			_progressIcon3, 
			_progressIcon4, 
			_progressIcon5, 
			_progressIcon6
	));
	
	@FXML
	public void handleNextClick() {
		_page++;
		_numberLabel.setText(_page + ".");
		
		if (_page == 2) {
			_progressIcon2.setFill(Color.GREEN);
		} else if (_page == 3) {
			_progressIcon3.setFill(Color.GREEN);
		} else if (_page == 4) {
			_progressIcon4.setFill(Color.GREEN);
		} else if (_page == 5) {
			_progressIcon5.setFill(Color.GREEN);
		} else if (_page == 6) {
			_progressIcon6.setFill(Color.GREEN);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_numberLabel.setText(_page + ".");
		_progressIcon1.setFill(Color.GREEN);
	}

}
