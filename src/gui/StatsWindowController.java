package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import tatai.game.TataiGame;

/**
 * The controller for the stats view
 * 
 * @author Nathan Cairns
 *
 */
public class StatsWindowController extends TataiController implements Initializable{
	
	private TataiGame _game;
	
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
	
	/**
	 * Constructor
	 */
	public StatsWindowController() {
		_game = Context.getInstance().currentGame();
	}
	
	/**
	 * Upon initialization.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_statLabel.setText(_game.averageAsPercent());
		_statTitleLabel.setText("Average Score");
		_averageButton.setText(_game.averageAsPercent());
		_correctButton.setText("" + _game.correct());
		_incorrectButton.setText("" + _game.incorrect());
		_totalButton.setText("" + _game.totalPlayed());
	}
	
	/**
	 * Change the stats label
	 * @param text the text to change the label to
	 * @param paint the color to set the text
	 */
	public void changeLabel(String text, String descripton, Paint paint) {
		String paintHex = paint.toString();
		paintHex = "#" +  paintHex.substring(2, paintHex.length() - 2);
		
		_statLabel.setText(text);
		_statLabel.setStyle("-fx-border-color: " +  paintHex + "; -fx-text-fill: " + paintHex +";");
		
		_statTitleLabel.setText(descripton);
		_statTitleLabel.setTextFill(paint);
	}
	
	@FXML
	public void handleAverageButtonClick() {
		changeLabel("" + _game.averageAsPercent(), TataiGame.AVERAGE, _averageButton.getTextFill());
	}
	
	@FXML
	public void handleCorrectButtonClick() {
		changeLabel("" + _game.correct(), TataiGame.CORRECT, _correctButton.getTextFill());
	}
	
	/**
	 * Change the stats label to the incorrect
	 */
	@FXML
	public void handleIncorrectButtonClick() {
		changeLabel("" + _game.incorrect(), TataiGame.INCORRECT, _incorrectButton.getTextFill());
	}
	
	/**
	 * Change the stats label to the total played/
	 */
	@FXML 
	public void handleTotalButtonClick() {
		changeLabel("" + _game.totalPlayed(), TataiGame.TOTAL_PLAYED, _totalButton.getTextFill());
	}
	
	/**
	 * When clicked return to main menu
	 */
	@FXML
	public void handleExitButtonClick() {
		Stage stage = (Stage) _exitButton.getScene().getWindow();
		changeWindow("MainWindow.fxml", stage);
	}
	
}
