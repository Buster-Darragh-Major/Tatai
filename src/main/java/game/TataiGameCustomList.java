package main.java.game;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import main.java.creations.creation.Creation;
import main.java.creations.creation.TataiCreation;
import main.java.creations.labelgenerator.CustomEquationLabelGenerator;
import main.java.creations.model.TataiCreationModel;
import main.java.questionlist.TextQuestionListHandler;

public class TataiGameCustomList extends TataiGameEquation {

	/* MACROS */
	private static final String CORRECT = "Correct";
	private static final String INCORRECT = "Incorrect";
	
	/* FIELDS */
	private TataiCreationModel _creationModel;
	private TextQuestionListHandler _handler;
	public final int TOTAL_NUMBER_OF_QUESTIONS;
	private int _questionNo = 0;
	
	// Constructor
	public TataiGameCustomList(TextQuestionListHandler handler) {
		super();
		_handler = handler;
		TOTAL_NUMBER_OF_QUESTIONS = _handler.size();
		_creationModel = new TataiCreationModel(new CustomEquationLabelGenerator(_handler), _handler.size());
		_hasStarted = true;
	}
	
	@Override
	public int totalNumberOfQuestions() {
		return TOTAL_NUMBER_OF_QUESTIONS;
	}
	
	@Override
	public String translateCurrentQuestion() {
		String label = _handler.getLine(_questionNo + 1);
		
		return _translator.translate(label);
	}
	
	@Override
	public void setLevel(Level level) {
		throw new GameException("Cannot set level in custom game");
	}
	
	@Override
	public Level currentLevel() {
		throw new GameException("Custom game has no level");
	}
	
	@Override
	public void displayCurrentQuestion(Label label, Pane pane) {
		_creationModel.displayCreation(_questionNo + 1, label, pane);
	}
	
	@Override
	public boolean answerQuestion(boolean answer) {

		if (answer) {
			_questionsCorrect.add(CORRECT);
			_correct++;
			nextQuestion();	
			return true;
		} else {
			if (_firstAttempt) {
				_questionsCorrect.add(INCORRECT);
				_incorrect++;
				nextQuestion();
			} else {
				_firstAttempt = true;
			}
			return false;
		}
	}
	
	protected void nextQuestion() {
		_firstAttempt = false;
		_questionNo++;
	}
	
	@Override
	public String getLevelHeader() {
		return "Custom List";
	}
	
	@Override
	public String getLevelDescription() {
		return "Play questions from your custom lists";
	}
	
	public <T extends Creation> void populateModel() {
		@SuppressWarnings("unchecked")
		Class<T> creationClass = (Class<T>) TataiCreation.class;

		_creationModel.setLabelingStrategy(new CustomEquationLabelGenerator(_handler));
		
		_creationModel.updateModel(creationClass);
	}
	
	@Override
	public void endGame() {
		if (_hasStarted) {
			// No stats updates
			_hasStarted = false;
		} else {
			throw new GameException("Game has already ended");
		}
	}
	
	@Override
	public ArrayList<String> getQuestionInts() {
		List<Creation> creations = _creationModel.listCreations();
		ArrayList<String> ints = new ArrayList<String>();
		
		for (int i = 0; i < _handler.size(); i++) {
			ints.add(i, creations.get(i).label());
		}
		
		return ints;
	}
	
	@Override
	public ArrayList<String> getQuestionTrans() {
		ArrayList<String> trans = new ArrayList<String>();
		
		for (int i = 0; i < _handler.size(); i++) {
			String creation = _creationModel.getCreationLabel(i + 1);
			trans.add(i, _translator.translate(creation));
		}
		
		return trans;
	}
	
	@Override
	public int currentQuestion() {
		return _questionNo + 1;
	}
}
