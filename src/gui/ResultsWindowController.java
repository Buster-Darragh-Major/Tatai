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

public class ResultsWindowController extends TataiController implements Initializable {

	@FXML
	private Label _scoreLabel;
	@FXML
	private TableView<Question> _resultsTable;
	@FXML
	private TableColumn<Question, String> qNo, qInt, qTranslation, qCorrect;
	
	
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
			_resultsTable.getItems().add(new Question(i + 1 + "", ints.get(i), trans.get(i), correct.get(i)));
		}
		
		// Set up Columns
		qNo.setCellValueFactory(new PropertyValueFactory<Question, String>("qNo"));
		qInt.setCellValueFactory(new PropertyValueFactory<Question, String>("qInt"));
		qTranslation.setCellValueFactory(new PropertyValueFactory<Question, String>("qTranslation"));
		qCorrect.setCellValueFactory(new PropertyValueFactory<Question, String>("qCorrect"));
			
		colorRows();
	}
	
	
	
	
	public static class Question {
		
		private final SimpleStringProperty qNo;
		private final SimpleStringProperty qInt;
		private final SimpleStringProperty qTranslation;
		private final SimpleStringProperty qCorrect;
		
		public Question(String questNo, String questInt, String questTranslation, String questCorrect) {
			qNo = new SimpleStringProperty(questNo);
			qInt = new SimpleStringProperty(questInt);
			qTranslation = new SimpleStringProperty(questTranslation);
			qCorrect = new SimpleStringProperty(questCorrect);
		}
		
		public StringProperty qNoProperty() {
			return qNo;
		}
		
		public String getQNo() {
			return qNo.get();
		}
		
		public void setQNo(String questNo) {
			qNo.set(questNo);
		}
		
		public StringProperty qIntProperty() {
			return qInt;
		}
		
		public String getQInt() {
			return qInt.get();
		}
		
		public void setQInt(String questInt) {
			qInt.set(questInt);
		}
		
		public StringProperty qTranslationProperty() {
			return qTranslation;
		}
		
		public String getQTranslation() {
			return qTranslation.get();
		}
		
		public void setQTranslation(String questTranslation) {
			qTranslation.set(questTranslation);
		}
		
		public StringProperty qCorrectProperty() {
			return qCorrect;
		}
		
		public String getQCorrect() {
			return qCorrect.get();
		}
		
		public void setQCorrect(String questCorrect) {
			qCorrect.set(questCorrect);
		}
	}
	
	
	
	private void colorRows() {
		_resultsTable.setBorder(new Border(new BorderStroke[0]));
		_resultsTable.setStyle("-fx-background-color: " + INCORRECT_RED);
	}
}
