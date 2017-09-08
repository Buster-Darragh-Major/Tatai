package creations.tatai;

import java.util.ArrayList;
import java.util.List;

import creations.cr.Creation;
import creations.ml.CreationModel;
import creations.tatai.numbergenerator.Level1RandomNumberGenerator;
import creations.tatai.numbergenerator.Level2RandomNumberGenerator;
import creations.tatai.numbergenerator.RandomNumberGenerator;

/**
 * Class representing the model of the current set of creations. Holds the list of
 * creations in the model, and allows adding and retrieval of creations, as well as
 * default population based on difficulty settings.
 * 
 * @author Buster Major
 */
public class TataiCreationModel extends CreationModel{
	/*MACROS*/
	public static final int NUMBER_OF_CREATIONS = 10;
	
	private RandomNumberGenerator _randomNumberStrategy;
	private List<Creation> _creations = new ArrayList<Creation>();
	private Level _level;

	/**
	 * Sets the difficulty level of the current population creation model. The difficulty
	 * will effect the model by determining what range of numbers are set to creations.
	 * 
	 * @param level
	 */
	public void setLevel(Level level) {
		_level = level;
	}
	
	/**
	 * Returns the creation needed for the corresponding question number. Returns the
	 * creation placed in the list for a specific question in the quiz.
	 * 
	 * @param questionNo
	 * @return Creation : TataiCreation
	 */
	public Creation getCreation(int questionNo) {
		// Questions go from 1-10, list indexes go from 0-9
		return _creations.get(questionNo - 1);
	}
	
	/**
	 * Adds a singular creation to the model, appends to the END of the creations list.
	 */
	@Override
	public void addCreation(Creation creation) {
		_creations.add(creation);
	}
	
	/**
	 * Create a list of creations corresponding to the current level
	 * 
	 * @param level The level to generate creations for.
	 * 
	 * @return The generated list of creations
	 */
	public void populateModel() {
		switch (_level) {
		case Level1: _randomNumberStrategy = new Level1RandomNumberGenerator();
			break;
		case Level2: _randomNumberStrategy = new Level2RandomNumberGenerator();
			break;
		}
		
		_creations = generateCreation();
	}
	
	/**
	 * Generates 10 creations.
	 * 
	 * @return the 10 creations.
	 */
	private List<Creation> generateCreation() {
		List<Creation> creationList = new ArrayList<Creation>();
		
		for (int i = 0; i < NUMBER_OF_CREATIONS; i++) {
			int number = _randomNumberStrategy.generateNumber();
			Creation creation = new TataiCreation(number);
			creationList.add(creation);
		}
		
		return creationList;	
	}

	/**
	 * Not particularly relevant to Tatai
	 */
	@Override
	public void addCreation(String creationName) {
		// Not relevant
	}

	/**
	 * Not particularly relevant to Tatai
	 */
	@Override
	protected void updateModel() {
		// Not relevant
	}

}
