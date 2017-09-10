package tatai.creations;

import creations.cr.Creation;
import creations.cr.CreationException;
import javafx.scene.paint.Color;

/**
 * This class holds the data needed for a creation object specific to the Tatai
 * learning aid. An object will hold an integer value, its corresponding Maori
 * word as a String, and colors for the background and foreground to be
 * displayed on the GUI
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
	public TataiCreation(String number, Color backgroundColor, Color fontColor) {
		super(number, backgroundColor, fontColor);

		try {
			int num = Integer.parseInt(number);

		} catch (NumberFormatException e) {
			throw new CreationException("Label must be a number between 1 and 99");
		}

	}

	@Override
	public void play() {
		// Not relevant

	}
}
