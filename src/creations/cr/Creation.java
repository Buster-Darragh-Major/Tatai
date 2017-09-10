package creations.cr;

import javafx.scene.paint.Color;

/**
 * Interface to represent a type which can have various file operations executed on it.
 * 
 * @author Nathan Cairns
 *
 */
public abstract class Creation {
	
	protected String _label;
	protected Color _bgColor;
	protected Color _fontColor;
	
	/**
	 * Plays the file
	 */
	public abstract void play();
	
	/**
	 * Returns the integer number of the creation
	 * 
	 * @return int : creation number value
	 */
	public String number() {
		return _label;
	}
	
	/**
	 * Returns the color value corresponding to the text of the creation
	 * 
	 * @return Color : javafx color
	 */
	public Color textColor() {
		return _fontColor;
	}
	
	/**
	 * Returns the color value corresponding to the background of the creation
	 * 
	 * @return Color : javafx color
	 */
	public Color backgroundColor() {
		return _bgColor;
	}
}
