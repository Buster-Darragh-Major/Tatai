package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tatai.creations.Level;
import tatai.game.TataiGame;
import tatai.game.TataiQuestionTableAdapter;

public class ResultsWindowController extends TataiController implements Initializable {

	/* FXML Nodes */
	@FXML
	private Label _scoreLabel;
	@FXML
	private Button _mainMenuButton;
	@FXML
	private Button _level2Button;
	@FXML
	private TableView<TataiQuestionTableAdapter> _resultsTable;
	@FXML
	private TableColumn<TataiQuestionTableAdapter, String> qNo, qInt, qTranslation, qCorrect;

	/* Fields */
	private int _questionTotal = TataiGame.TOTAL_NUMBER_OF_QUESTIONS;

	/**
	 * Handles user pressing main menu button
	 */
	@FXML
	public void handleMainMenuButtonClick() {
		Stage stage = (Stage) _level2Button.getScene().getWindow(); // Get current Stage
		changeWindow("LevelSelectWindow.fxml", stage); // Change to MainWindow.fxml view
	}

	/**
	 * Handles user pressing level 2 button but continuing immediately to level 2
	 */
	@FXML
	public void handleLevel2ButtonClick() {
		// Create new game, set to level 2 and begin game
		Context.getInstance().newGame();
		Context.getInstance().currentGame().setLevel(Level.Level2);
		Context.getInstance().currentGame().startGame();

		Stage stage = (Stage) _mainMenuButton.getScene().getWindow(); // Get current stage
		changeWindow("GameWindow.fxml", stage); // Change to GameWindow.fxml view
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// If on level 1 and got 8 or more correct offer user to play level 2
		if (!((Context.getInstance().currentGame().questionsCorrect() >= 8)
				&& (Context.getInstance().currentGame().currentLevel() == Level.Level1))) {
			_level2Button.setVisible(false);
		}

		// Set overall questions correct label
		_scoreLabel
				.setText("You scored " + Context.getInstance().currentGame().questionsCorrect() + "/" + _questionTotal);

		// Form Table:
		// Unpack data from game class into readable data for table tree view
		ArrayList<String> ints = Context.getInstance().currentGame().getQuestionInts();
		ArrayList<String> trans = Context.getInstance().currentGame().getQuestionTrans();
		ArrayList<String> correct = Context.getInstance().currentGame().getQuestionCorrect();
		for (int i = 0; i < _questionTotal; i++) {
			_resultsTable.getItems().add(new TataiQuestionTableAdapter(i + 1 + ")", ints.get(i), trans.get(i), correct.get(i)));
		}

		// Set up Columns
		qNo.setCellValueFactory(new PropertyValueFactory<TataiQuestionTableAdapter, String>("qNo"));
		qInt.setCellValueFactory(new PropertyValueFactory<TataiQuestionTableAdapter, String>("qInt"));
		qTranslation.setCellValueFactory(new PropertyValueFactory<TataiQuestionTableAdapter, String>("qTranslation"));
		qCorrect.setCellValueFactory(new PropertyValueFactory<TataiQuestionTableAdapter, String>("qCorrect"));
	}
}
