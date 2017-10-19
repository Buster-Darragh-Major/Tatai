package tatai.game;

import gui.Context;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import res.questionlist.TextQuestionListHandler;
import tatai.creations.Level;

public class TataiGameCustomList extends TataiGameEquation {

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
		
	}
}
