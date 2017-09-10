package creations.ml;

import java.util.ArrayList;
import java.util.List;

import creations.cr.Creation;

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
	
	protected List<Creation> _creations = new ArrayList<Creation>();
	
	/**
	 * Constructor.
	 * Instantiate the map and update the model.
	 */
	public CreationModel() {
		_creations = new ArrayList<Creation>();
	}
	
	/**
	 * update the model
	 * @param <T>
	 */
	public abstract <T extends Creation> void updateModel(Class<T> creationClass);

	
	/**
	 * Return the names of the creations stored in the model as a 
	 * list.
	 * 
	 * @return List<String>: the list of creation names.
	 */
	public List<Creation> listCreations() {
		
		return _creations;
	}
	
}
