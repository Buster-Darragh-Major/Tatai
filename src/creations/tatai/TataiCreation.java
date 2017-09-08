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
public class TataiCreation implements Creation {
	
	// Approved colors for background - light, non-harsh
	private Color[] _bgColors = new Color[] {
			Color.web("#EE91D8"), // Pastel Pink
			Color.web("#C491EE"), // Pastel Purple
			Color.web("#9198EE"), // Pastel Blue
			Color.web("#91EEE9"), // Pastel Cyan
			Color.web("#EE9191"), // Pastel Red
			Color.web("#EEB791"), // Pastel Orange
			Color.web("#E6EE91"), // Pastel Yellow
			Color.web("#90EE90"), // Pastel Green
			Color.web("#FFF480"), // Stronger Yellow
			Color.web("#FF8585"), // Stronger Red
			Color.web("#85FF8b"), // Stronger Green
			Color.web("#85ABFF"), // Stronger Baby Blue
			Color.web("#BC85FF"), // Stronger Violet
			Color.web("#FF85F9"), // Stronger Pink
			Color.web("#FF85AD")  // Stronger Salmon Pink
	};
	
	// Approved colors for text - darker, higher contrast
	private Color[] _txtColors = new Color[] {
			Color.web("#C20A0A"), // Dark Red
			Color.web("#C24A0A"), // Dark Orange
			Color.web("#1B9608"), // Dark Green
			Color.web("#078D67"), // Dark Cyan
			Color.web("#084891"), // Dark Blue
			Color.web("#1A0891"), // Dark Deep Blue
			Color.web("#830726"), // Brown
			Color.web("#05009E"), // Richer Blue
			Color.web("#730099"), // Richer Purple
			Color.web("#009459"), // Richer Bluegreen
			Color.web("#008D94"), // Richer Cyan
			Color.web("#029400"), // Richer Green
			Color.web("#940000"), // Richer Red
			Color.web("#944F00"), // Richer Orange
			Color.web("#910094")  // Richer Violet
	};
	
	private int _num;
	private Color _bgColor;
	private Color _txtColor;
	private String _maoriName;
	
	public TataiCreation(int number, Color backgroundColor, Color fontColor) {
		if ((number > 99) || (number < 1)) {
			throw new CreationException("Creation integer value must be between 1 and 99");
		}
		_num = number;
		_bgColor = backgroundColor;
		_txtColor = fontColor;
	}

	/**
	 * Retrieves the integer value stored in the creation
	 */
	@Override
	public int number() {
		return _num;
	}

	/**
	 * Retrieves the text color stored in the creation
	 */
	@Override
	public Color textColor() {
		return _txtColor;
	}

	/**
	 * Retrieves the background color stored in the creation
	 */
	@Override
	public Color backgroundColor() {
		return _bgColor;
	}

	@Override
	public void play() {
		// Not relevant
		
	}
}
