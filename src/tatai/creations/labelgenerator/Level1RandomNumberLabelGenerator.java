package tatai.creations.labelgenerator;
/**
 * Generates a number for level one.
 * The range is 1 - 10.
 * 
 * @author Nathan Cairns
 *
 */
public class Level1RandomNumberLabelGenerator implements LabelGenerator {
	/*MACROS*/
	public static final int MAX = 10;
	public static final int MIN = 1;
	
	/**
	 * generate a random number between 1 and 10
	 */
	@Override
	public String generateLabel() {
		int number = getRandomInteger(MAX, MIN);
		
		return "" + number;
	}
	
	public int getRandomInteger(int maximum, int minimum) {
		return ((int) (Math.random()*(maximum))) + minimum;
	}

}