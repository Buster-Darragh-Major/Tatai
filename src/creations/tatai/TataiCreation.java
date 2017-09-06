package creations.tatai;

import creations.cr.Creation;
import creations.cr.CreationException;
import javafx.scene.paint.Color;

/**
 * This class holds the data needed for a creation object specific to the Tatai
 * learning aid. An object will hold an integer value, its corresponding Maori
 * word as a String, and colors for the background and foreground to be displayed
 * on the GUI/
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
	};
	
	private Color[] _txtColors = new Color[] {
			Color.web("#C20A0A"), // Dark Red
			Color.web("#C24A0A"), // Dark Orange
			Color.web("#1B9608"), // Dark Green
			Color.web("#078D67"), // Dark Cyan
			Color.web("#084891"), // Dark Blue
			Color.web("#1A0891"), // Dark Deep Blue
			Color.web("#830726"), // Brown
	};
	
	private int _num;
	private Color _bgColor;
	private Color _txtColor;
	
	public TataiCreation(int num) {
		if ((num > 99) || (num < 1)) {
			throw new CreationException("Creation integer value must be between 1 and 99");
		}
		
		_num = num;
		_bgColor = _bgColors[(int) Math.round((_bgColors.length * Math.random())) - 1];
		_txtColor = _txtColors[(int) Math.round((_txtColors.length * Math.random())) - 1];
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String maoriName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int number() {
		return _num;
	}

	@Override
	public Color textColor() {
		return _txtColor;
	}

	@Override
	public Color backgroundColor() {
		return _bgColor;
	}
}
