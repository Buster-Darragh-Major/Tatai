package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import stats.TataiQuestion;

public class ResultsWindowController extends TataiController implements Initializable {

	@FXML
	private Label _scoreLabel;
	@FXML
	private TableView<TataiQuestion> _resultsTable;
	@FXML
	private TableColumn<TataiQuestion, String> qNo, qInt, qTranslation, qCorrect;
	
	
	@SuppressWarnings("static-access")
	private int _questionTotal = Context.getInstance().currentGame().TOTAL_NUMBER_OF_QUESTIONS;
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		_scoreLabel.setText("You scored " + Context.getInstance().currentGame().questionsCorrect() + 
				"/" + _questionTotal);
		
		// Unpack data from game class into readable data for table tree view
		ArrayList<String> ints = Context.getInstance().currentGame().getQuestionInts();
		ArrayList<String> trans = Context.getInstance().currentGame().getQuestionTrans();
		ArrayList<String> correct = Context.getInstance().currentGame().getQuestionCorrect();
		for (int i = 0; i < _questionTotal; i++) {
			_resultsTable.getItems().add(new TataiQuestion(i + 1 + "", ints.get(i), trans.get(i), correct.get(i)));
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
