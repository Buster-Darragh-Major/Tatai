package creations.tatai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import creations.cr.Creation;
import javafx.scene.paint.Color;
/**
 * 
 * @author Nathan Cairns
 * @author Buster Darragh-Major
 *
 */
public class TataiCreationBuilder {
	
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
	
	public List<Creation> populateList(Level level) {
		if (level == Level.Level1) {
			return populateLevel1();
		} else if (level == Level.Level2) {
			return populateLevel2();
		}
		
		return null;
	}
	
	/**
	 * Populate TataiCreationModel list with 10 creations randomly generated with numbers
	 * 1 to 10, no number appears twice.
	 */
	private List<Creation> populateLevel1() {
		List<Creation> list = new ArrayList<Creation>();
		
		// Create list of numbers in order 1 to 10
		List<Integer> nums = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			nums.add(i, i + 1);
		}
		
		// Randomize order of numbers
		Collections.shuffle(nums);
		
		// Populate TataiCreationModel
		for (int i = 0; i < 10; i++) {
			TataiCreation c = new TataiCreation(nums.get(i));
			list.add(c);
		}
		
		return list;
	}
	
	/**
	 * Populate TataiCreationModel with 10 creations randomly generated with numbers
	 * 1 to 99, numbers may appear twice.
	 */
	private List<Creation> populateLevel2() {
		List<Creation> list = new ArrayList<Creation>();
		
		for (int i = 0; i < 10; i++) {
			int rand = (int) Math.round(Math.random() * 98) + 1;
			TataiCreation c = new TataiCreation(rand);
			list.add(c);
		}
		
		return list;
	}
}
