package tatai.creations;

import creations.cr.Creation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
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
	 * @param label
	 * @param backgroundColor
	 * @param fontColor
	 */
	public TataiCreation(String label, Color backgroundColor, Color fontColor) {
		super(label, backgroundColor, fontColor);
	}

	/**
	 * Displays the creation on a label and pane object
	 */
	@Override
	public void display(Label label, Pane pane) {
		label.setText(_label);
		label.setTextFill(_fontColor);
		label.setAlignment(Pos.CENTER);
		
		String bgColor = _bgColor.toString();
		bgColor = bgColor.substring(2, bgColor.length() - 2);
		
		pane.setStyle("-fx-background-color: #" + bgColor);
	}	
}
