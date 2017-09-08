package creations.tatai.numbergenerator;
/**
 * Generates a number for level one.
 * The range is 1 - 100.
 * 
 * @author Nathan Cairns
 *
 */
public class Level2RandomNumberGenerator implements RandomNumberGenerator{
	/*MACROS*/
	public static final int MAX = 100;
	public static final int MIN = 1;
	
	/**
	 * generate a random number between 1 and 100
	 */
	@Override
	public int generateNumber() {
		return (int) Math.round((Math.random() * MAX)) + MIN;
	}

}
