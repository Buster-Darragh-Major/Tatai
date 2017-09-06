package creations.cr;

import creations.cr.Creation;
import javafx.scene.paint.Color;

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
			Color.web("#c20a0a"), // Dark Red
			Color.web("#c24a0a"), // Dark Orange
			Color.web("#1b9608"), // Dark Green
			Color.web("#078d67"), // Dark Cyan
			Color.web("#084891"), // Dark Blue
			Color.web("#1a0891"), // Dark Deep Blue
			Color.web("#830726"), // Brown
	};
	
	private int _num;
	private Color _bgColor;
	private Color _txtColor;
	
	public TataiCreation(int num) {
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
