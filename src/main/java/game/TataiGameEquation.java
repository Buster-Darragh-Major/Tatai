package main.java.game;

import java.util.ArrayList;

import main.java.creations.creation.Creation;
import main.java.creations.creation.TataiCreation;
import main.java.creations.labelgenerator.Level1EquationLabelGenerator;
import main.java.creations.labelgenerator.Level2EquationLabelGenerator;
import main.java.creations.model.TataiCreationModel;
import main.java.translator.TataiEquationTranslator;
import main.java.translator.Translator;

/**
 * After TataiGame type has become degraded, this is the default game mode set in
 * Tatai on open. Games should always revert the game mode back to a TataiEquationGame
 * when any other game type is finished. Do not create another instance of this class,
 * one object should last an entire session.
 * 
 * Uncommented methods have same description as parent method **
 * 
 * @author buster
 */
public class TataiGameEquation extends TataiGame {

	/* Fields */
	protected Translator _translator = new TataiEquationTranslator();

	/**
	 * Constructor
	 */
	public TataiGameEquation() {
		super();
	}
	
	@Override
	public String getLevelDescription() {
		if (_level == Level.LEVEL1) {
			return ("Test your maths with addition and subtraction questions, answers range from 1-9.");
		} else if (_level == Level.LEVEL2) {
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
		
		if (_level == Level.LEVEL1 || _level == Level.LEVEL1_REVERSE) {
			_creationModel.setLabelingStrategy(new Level1EquationLabelGenerator());
		} else if (_level == Level.LEVEL2 || _level == Level.LEVEL2_REVERSE) {
			_creationModel.setLabelingStrategy(new Level2EquationLabelGenerator());
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
