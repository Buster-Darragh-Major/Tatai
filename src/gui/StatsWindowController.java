package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StatsWindowController extends TataiController implements Initializable{
	
	@FXML
	private Label _statLabel;	
	@FXML
	private Label _statTitleLabel;
	@FXML
	private Button _averageButton;
	@FXML 
	private Button _correctButton;
	@FXML
	private Button _incorrectButton;
	@FXML
	private Button _totalButton;
	@FXML
	private Button _exitButton;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_statLabel.setText(Context.getInstance().currentGame().averageAsPercent());
		_statTitleLabel.setText("Average Score");
		_averageButton.setText(Context.getInstance().currentGame().averageAsPercent());
		_correctButton.setText("" + Context.getInstance().currentGame().correct());
		_incorrectButton.setText("" + Context.getInstance().currentGame().incorrect());
		_totalButton.setText("" + Context.getInstance().currentGame().totalPlayed());
	}
	
}
