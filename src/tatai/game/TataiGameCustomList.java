package tatai.game;

import java.util.ArrayList;
import java.util.List;

import creations.cr.Creation;
import gui.Context;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import res.questionlist.TextQuestionListHandler;
import tatai.creations.Level;
import tatai.creations.TataiCreation;
import tatai.creations.TataiCreationModel;
import tatai.creations.labelgenerator.CustomEquationLabelGenerator;

public class TataiGameCustomList extends TataiGameEquation {

	private TataiCreationModel _creationModel;
	private TextQuestionListHandler _handler = new TextQuestionListHandler(Context.getInstance().currentQuestionList());
	public final int TOTAL_NUMBER_OF_QUESTIONS = _handler.size();
	
	// Constructor
	public TataiGameCustomList() {
		super();
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
		throw new GameException("Custm game has no level");
	}
	
	@Override
	public void displayCurrentQuestion(Label label, Pane pane) {
		_creationModel.displayCreation(_questionNo, label, pane);
	}
	
	private void nextQuestion() {
		_firstAttempt = false;
		if ((_questionNo < _handler.size() + 1) && (_questionNo > 0)) {
			_questionNo++;
		} else {
			_questionNo++;
			endGame();
		}
	}
	
	@Override
	public String getLevelHeader() {
		return "Custom List";
	}
	
	@Override
	public String getLevelDescription() {
		return "Play questions from your custom lists";
	}
	
	private <T extends Creation> void populateModel() {
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
}
