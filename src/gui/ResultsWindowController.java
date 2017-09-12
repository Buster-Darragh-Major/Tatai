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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.stage.Stage;
import stats.TataiQuestion;
import tatai.creations.Level;

public class ResultsWindowController extends TataiController implements Initializable {

	@FXML
	private Label _scoreLabel;
	@FXML
	private Button _mainMenuButton;
	@FXML
	private Button _level2Button;
	@FXML
	private TableView<TataiQuestion> _resultsTable;
	@FXML
	private TableColumn<TataiQuestion, String> qNo, qInt, qTranslation, qCorrect;
	
	@SuppressWarnings("static-access")
	private int _questionTotal = Context.getInstance().currentGame().TOTAL_NUMBER_OF_QUESTIONS;
	
	@FXML
	public void handleMainMenuButtonClick() {
		Stage stage = (Stage) _level2Button.getScene().getWindow(); // Get current Stage
		changeWindow("LevelSelectWindow.fxml", stage); // Change to MainWindow.fxml view
	}
	
	@FXML
	public void handleLevel2ButtonClick() {
		Context.getInstance().newGame();
		Context.getInstance().currentGame().setLevel(Level.Level2);
		Context.getInstance().currentGame().startGame();
		
		Stage stage = (Stage) _mainMenuButton.getScene().getWindow(); // Get current stage
		changeWindow("GameWindow.fxml", stage); // Change to GameWindow.fxml view
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// If on level 1 and got 8 or more correct offer user to play level 2
		if (!((Context.getInstance().currentGame().questionsCorrect() >= 8) && 
				(Context.getInstance().currentGame().currentLevel() == Level.Level1))) {
			_level2Button.setVisible(false);
		}
		
		// Set overall questions correct label
		_scoreLabel.setText("You scored " + Context.getInstance().currentGame().questionsCorrect() + 
				"/" + _questionTotal);
		
		// Form Table:
		// Unpack data from game class into readable data for table tree view
		ArrayList<String> ints = Context.getInstance().currentGame().getQuestionInts();
		ArrayList<String> trans = Context.getInstance().currentGame().getQuestionTrans();
		ArrayList<String> correct = Context.getInstance().currentGame().getQuestionCorrect();
		for (int i = 0; i < _questionTotal; i++) {
			_resultsTable.getItems().add(new TataiQuestion(i + 1 + ")", ints.get(i), trans.get(i), correct.get(i)));
		}
		
		// Set up Columns
		qNo.setCellValueFactory(new PropertyValueFactory<TataiQuestion, String>("qNo"));
		qInt.setCellValueFactory(new PropertyValueFactory<TataiQuestion, String>("qInt"));
		qTranslation.setCellValueFactory(new PropertyValueFactory<TataiQuestion, String>("qTranslation"));
		qCorrect.setCellValueFactory(new PropertyValueFactory<TataiQuestion, String>("qCorrect"));
			
		colorRows();
	}
	
	
	private void colorRows() {
		_resultsTable.setBorder(new Border(new BorderStroke[0]));
		_resultsTable.setStyle("-fx-background-color: " + INCORRECT_RED);
	}
}
