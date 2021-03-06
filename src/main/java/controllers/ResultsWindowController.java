package main.java.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.game.Level;
import main.java.game.TataiQuestionTableAdapter;

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
	@FXML 
	private Label _personalBestLabel;

	/* Fields */
	private int _questionTotal = Context.getInstance().currentGame().totalNumberOfQuestions();

	/**
	 * Handles user pressing main menu button
	 */
	@FXML
	public void handleMainMenuButtonClick() {
		changeWindow(LEVEL_SELECT_FXML, _level2Button); // Change to MainWindow.fxml view
	}

	/**
	 * Handles user pressing level 2 button but continuing immediately to level 2
	 */
	@FXML
	public void handleLevel2ButtonClick() {
		// Create new game, set to level 2 and begin game
		Context.getInstance().currentGame().setLevel(Level.LEVEL2);
		Context.getInstance().currentGame().startGame();

		changeWindow(GAME_FXML, _mainMenuButton); // Change to GameWindow.fxml view
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_mainMenuButton.requestFocus();

		// Set overall questions correct label
		_scoreLabel
				.setText("You scored " + Context.getInstance().currentGame().questionsCorrect() + "/" + _questionTotal);

		// Form Table:
		// Unpack data from game class into readable data for table tree view
		ArrayList<String> ints = Context.getInstance().currentGame().getQuestionInts();
		ArrayList<String> trans = Context.getInstance().currentGame().getQuestionTrans();
		ArrayList<String> correct = Context.getInstance().currentGame().getQuestionCorrect();
		for (int i = 0; i < _questionTotal; i++) {
			_resultsTable.getItems()
					.add(new TataiQuestionTableAdapter(i + 1 + ")", ints.get(i), trans.get(i), correct.get(i)));
		}

		// Set up Columns
		qNo.setCellValueFactory(new PropertyValueFactory<TataiQuestionTableAdapter, String>("qNo"));
		qInt.setCellValueFactory(new PropertyValueFactory<TataiQuestionTableAdapter, String>("qInt"));
		qTranslation.setCellValueFactory(new PropertyValueFactory<TataiQuestionTableAdapter, String>("qTranslation"));
		qCorrect.setCellValueFactory(new PropertyValueFactory<TataiQuestionTableAdapter, String>("qCorrect"));
		// If on level 1 and got 8 or more correct offer user to play level 2

		if (!((Context.getInstance().currentGame().questionsCorrect() >= 8)
				&& (Context.getInstance().currentGame().currentLevel() == Level.LEVEL1))) {
			_level2Button.setVisible(false);

			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					_level2Button.requestFocus();
				}
			});
		}

		// Try check if personal best exists for game type. If not, ignore and end game.
		try {
			// All game functionality is complete. Restore default game mode to equation
			// mode so further games are able to be played.
			int oldBest = Context.getInstance().currentGame().personalBest();
			Context.getInstance().currentGame().endGame();
			isPersonalBest(oldBest);
			Context.getInstance().setGameToEquation();
			
		} catch (NullPointerException e) {
			
			Context.getInstance().currentGame().endGame();
			Context.getInstance().setGameToEquation();
		}
	}

	/**
	 * This method determines whether the user has scored a personal best.
	 * If they have the personal best label is displayed.
	 * @param oldBest
	 *            the users old personal best
	 * @return whether their new personal best was better than the old
	 */
	private boolean isPersonalBest(int oldBest) {
		int newBest = Context.getInstance().currentGame().personalBest();
		if (newBest > oldBest) {
			_personalBestLabel.setVisible(true);
			return true;
		} else {
			_personalBestLabel.setVisible(false);
			return false;
		}
	}
}
