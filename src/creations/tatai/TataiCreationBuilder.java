package creations.tatai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import creations.cr.Creation;
import creations.tatai.numbergenerator.Level1RandomNumberGenerator;
import creations.tatai.numbergenerator.Level2RandomNumberGenerator;
import creations.tatai.numbergenerator.RandomNumberGenerator;
import javafx.scene.paint.Color;
/**
 * This class deals with generating creations
 * 
 * @author Nathan Cairns
 * @author Buster Darragh-Major
 *
 */
public class TataiCreationBuilder {
	/*MACROS*/
	public static final int NUMBER_OF_CREATIONS = 10;
	
	private RandomNumberGenerator _randomNumberStrategy;
	
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
	
	/**
	 * Create a list of creations corresponding to the current level
	 * 
	 * @param level The level to generate creations for.
	 * 
	 * @return The generated list of creations
	 */
	public List<Creation> populateList(Level level) {
		switch (level) {
		case Level1: _randomNumberStrategy = new Level1RandomNumberGenerator();
			break;
		case Level2: _randomNumberStrategy = new Level2RandomNumberGenerator();
			break;
		}
		
		return generateCreation();
	}
	
	/**
	 * Generates 10 creations.
	 * 
	 * @return the 10 creations.
	 */
	private List<Creation> generateCreation() {
		List<Creation> creationList = new ArrayList<Creation>();
		
		for (int i = 0; i < NUMBER_OF_CREATIONS; i++) {
			int number = _randomNumberStrategy.generateNumber();
			Creation creation = new TataiCreation(number);
			creationList.add(creation);
		}
		
		return creationList;
		
	}
}
