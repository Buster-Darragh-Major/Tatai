package creations.cr;

import javafx.scene.paint.Color;

/**
 * Interface to represent a type which can have various file operations executed on it.
 * 
 * @author Nathan Cairns
 *
 */
public interface Creation {
	/**
	 * Creates the file
	 */
	public void create();
	
	/**
	 * Plays the file
	 */
	public void play();
	
	/**
	 * Deletes the file
	 */
	public void delete();
	
	/**
	 * Returns the name of the file
	 * 
	 * @return String : creation name
	 */
	public String maoriName();
	
	/**
	 * Returns the integer number of the creation
	 * 
	 * @return int : creation number value
	 */
	public int number();
	
	/**
	 * Returns the color value corresponding to the text of the creation
	 * 
	 * @return Color : javafx color
	 */
	public Color textColor();
	
	/**
	 * Returns the color value corresponding to the background of the creation
	 * 
	 * @return Color : javafx color
	 */
	public Color backgroundColor();
}
