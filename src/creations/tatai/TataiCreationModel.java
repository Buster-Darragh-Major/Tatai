package creations.tatai;

import java.util.ArrayList;
import java.util.List;

import creations.cr.Creation;
import creations.ml.CreationModel;

/**
 * Class representing the model of the current set of creations. Holds the list of
 * creations in the model, and allows adding and retrieval of creations, as well as
 * default population based on difficulty settings.
 * 
 * @author Buster Major
 */
public class TataiCreationModel extends CreationModel{
	
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
	 * Populates the model of the creations and adds them to _creations list. Behavior
	 * of list population depends on the current level difficulty set in the model object.
	 */
	public void populateModel() {
		TataiCreationBuilder b = new TataiCreationBuilder();
		_creations.addAll(b.populateList(_level));
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
