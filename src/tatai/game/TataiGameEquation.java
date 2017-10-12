package tatai.game;

import java.util.ArrayList;

import creations.cr.Creation;
import stats.CSVStatsHandler;
import stats.StatisticHandler;
import tatai.creations.Level;
import tatai.creations.TataiCreation;
import tatai.creations.labelgenerator.Level1EquationLabelGenerator;
import tatai.creations.labelgenerator.Level2EquationLabelGenerator;

public class TataiGameEquation extends TataiGame {

	/* Fields */
	private StatisticHandler _l01statsHandler = new CSVStatsHandler(Level.Level1);
	private StatisticHandler _l02statsHandler = new CSVStatsHandler(Level.Level2);
	// ^^^ REPLACE WITH PROPER STATS HANDLERS FOR GAME TYPE ^^^ //

	
	@Override
	public String getLevelDescription() {
		if (_level == Level.Level1) {
			return ("Test your maths with addition and subtraction questions, answers range from 1-9.");
		} else if (_level == Level.Level2) {
			return ("Test your maths with addition, subtraction, multiplication and division questions." 
		+ " Answers might range from 1-9 or 1-99.");
		}
		return null;	
	}
	
	@Override
	public void startGame() {
		if (!_hasStarted) {
			populateModel();
			
			_correct = 0;
			_incorrect = 0;
			_questionNo = 1;
			_hasStarted = true;
			_firstAttempt = false;
			_questionsCorrect = new ArrayList<String>();
			
			if (_level == Level.Level1) {
				_statsHandler = _l01statsHandler;
			} else if (_level == Level.Level2) {
				_statsHandler = _l02statsHandler;
			}
			
		} else {
			throw new GameException("Game has already started");
		}
	}
	
	@Override
	public boolean answerQuestion(boolean answer) {
		return answer;
	}
	
	private <T extends Creation> void populateModel() {
		@SuppressWarnings("unchecked")
		Class<T> creationClass = (Class<T>) TataiCreation.class;

		switch (_level) {
		case Level1:
			_creationModel.setLabelingStrategy(new Level1EquationLabelGenerator());
			break;
		case Level2:
			_creationModel.setLabelingStrategy(new Level2EquationLabelGenerator());
			break;
		}
		
		_creationModel.updateModel(creationClass);
	}
}
