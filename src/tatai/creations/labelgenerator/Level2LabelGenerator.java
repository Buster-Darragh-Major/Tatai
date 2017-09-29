package tatai.creations.labelgenerator;
/**
 * Generates a number for level one.
 * The range is 1 - 100.
 * 
 * @author Nathan Cairns
 *
 */
public class Level2LabelGenerator extends Level1LabelGenerator{
	/*MACROS*/
	public static final int MAX = 99;
	public static final int MIN = 1;
	
	/**
	 * generate a random number between 1 and 100
	 */
	@Override
	public String generateLabel() {
		int number = getRandomInteger(MAX, MIN);
		
		return "" + number;
	}

}
