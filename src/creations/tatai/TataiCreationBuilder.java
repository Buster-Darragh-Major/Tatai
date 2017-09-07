package creations.tatai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import creations.cr.Creation;

public class TataiCreationBuilder {

	public enum Level {
		Level1, Level2
	}
	
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
