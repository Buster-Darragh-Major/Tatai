package creations.tatai;

import creations.cr.Creation;
import creations.cr.CreationException;
import javafx.scene.paint.Color;

/**
 * This class holds the data needed for a creation object specific to the Tatai
 * learning aid. An object will hold an integer value, its corresponding Maori
 * word as a String, and colors for the background and foreground to be displayed
 * on the GUI
 * 
 * @author Buster Major
 */
public class TataiCreation extends Creation {
	
	/**
	 * Constructor 
	 * 
	 * @param number
	 * @param backgroundColor
	 * @param fontColor
	 */
	public TataiCreation(int number, Color backgroundColor, Color fontColor) {
		super();
		
		if ((number > 99) || (number < 1)) {
			throw new CreationException("Creation integer value must be between 1 and 99");
		}
		
		_label = "" + number;
		_bgColor = backgroundColor;
		_fontColor = fontColor;
	}

	@Override
	public void play() {
		// Not relevant
		
	}
}
