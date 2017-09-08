package creations.ml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import creations.cr.Creation;
import creations.cr.CreationException;

/**
 * This class allows a creation type object to be easily
 * used in the Maths Aid GUI. Provides a way of storing 
 * multiple creations and allows various operations to be 
 * performed on these creations.
 * 
 * @author Nathan Cairns
 *
 */
public abstract class CreationModel {
	
	// A map storing the creations in the model
	protected Map<String, Creation> _creations;
	
	/**
	 * Constructor.
	 * Instantiate the map and update the model.
	 */
	public CreationModel() {
		_creations = new HashMap<String, Creation>();
		
		updateModel();
	}
	
	/**
	 * update the model
	 */
	protected abstract void updateModel();

	
	/**
	 * Return the names of the creations stored in the model as a 
	 * list.
	 * 
	 * @return List<String>: the list of creation names.
	 */
	public List<String> listCreations() {
		List<String> creationList = new ArrayList<String>(_creations.keySet());
		
		return creationList;
	}
	
	/**
	 * Play a creation from the model.
	 * 
	 * @param creationName: the creation to be played
	 */
	public void playCreation(String creationName) { 
		Creation creationToPlay = getCreation(creationName);
		creationToPlay.play();
	}
	
	/**
	 * Get a creation from the model.
	 * 
	 * @param creationName: The name of the creation to get.
	 * @return Creation: the creation which was retrieved.
	 */
	protected Creation getCreation(String creationName) {
		if (creationName == null || creationName == "") {
			throw new CreationException("Invalid creation name (" + creationName + ")");
		} else if (!_creations.containsKey(creationName)){ 
			throw new CreationException("Creation (" + creationName + ") does not exist");
		}
		return _creations.get(creationName);
	}
	
	/**
	 * Check whether the model contains a creation.
	 * 
	 * @param creationName: The name of the creation to check.
	 * @return boolean: true if contains, false if not.
	 */
	public boolean containsCreation(String creationName) {
		if (creationName != null && !creationName.equals(null) && _creations.containsKey(creationName)) {
			return true;
		}
		return false;
	}
}
