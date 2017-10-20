package game;

import java.util.ArrayList;

import creations.cr.Creation;
import creations.cr.Level;
import creations.cr.TataiCreation;
import creations.cr.TataiCreationModel;
import creations.labelgenerator.Level1EquationLabelGenerator;
import creations.labelgenerator.Level2EquationLabelGenerator;
import stats.CSVStatsHandler;
import stats.StatisticHandler;
import translator.TataiEquationTranslator;
import translator.Translator;

public class TataiGameEquation extends TataiGame {

	/* Fields */
	protected Translator _translator = new TataiEquationTranslator();
	// ^^^ REPLACE WITH PROPER STATS HANDLERS FOR GAME TYPE ^^^ //

	public TataiGameEquation() {
		super();
	}
	
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
	public String translateCurrentQuestion() {
		String label = _creationModel.getCreationLabel(_questionNo);

		return _translator.translate(label);
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
			
		} else {
			throw new GameException("Game has already started");
		}
	}
	
	public <T extends Creation> void populateModel() {
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
	
	/**
	 * Returns a list of translated integers as a String in the order they
	 * were played in the game
	 */
	public ArrayList<String> getQuestionTrans() {
		ArrayList<String> trans = new ArrayList<String>();
		
		for (int i = 0; i < TataiCreationModel.DEFAULT_NUMBER_OF_CREATIONS; i++) {
			String creation = _creationModel.getCreationLabel(i + 1);
			trans.add(i, _translator.translate(creation));
		}
		
		return trans;
	}
	
	////Stats Methods \\\\
	
	/**
	 * Returns the average as a percentage 
	 * 
	 * return average
	 */
	public String averageAsPercent() {
		if (averageAsDouble() <= 0) {
			return "%" + 0.00;
		}
		
		String per = "" + (averageAsDouble() * 10);
		per = per.substring(0, 4) + "%";
		return per;
	}
		 
	/**
	 * Returns this sessions current average
	 * 
	 * @return The average as a double
	 */
	public double averageAsDouble() {
		return _currentUser.getAverage(_level);
	}
	
	/**
	 * Total played
	 */
	public int totalPlayed() {
		return _currentUser.getTotalPlayed(_level);
	}
	
	/**
	 * correct
	 */
	public int correct() {
		return _currentUser.getTotalCorrect(_level);
	}
	
	/**
	 * incorrect
	 */
	public int incorrect() {
		return _currentUser.getTotalIncorrect(_level);
	}
}
