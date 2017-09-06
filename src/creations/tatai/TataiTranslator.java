package creations.tatai;

/**
 * This class represents the ability to translate integers to strings representing
 * the relevant integer in Maori. 
 * 
 * Maori counting is structured fairly simply from 1 to 99. We will consider any 
 * number in this range to have 4 distinct components. A "multiplier", first in the
 * string telling how many tens there are, followed by "tens", in this case
 * represented by just "tekau", followed by "and", in this case "ma" and finally 
 * the value of the ones column.
 * For example: 
 * 84 = iwa-("multiplier") tekau-("tens") mā-("and") whā-("ones")
 *      iwa tekau mā whā
 * 
 * @author Buster Major
 */
public class TataiTranslator {
	
	private int _val;
	private String _asMaori;
	
	public TataiTranslator(int val) {
		_val = val;
		_asMaori = translate();
	}
	
	public String asMaori() {
		return _asMaori;
	}
	
	/**
	 * Private helper method to translate integer to Maori string
	 */
	private String translate() {
		String multiplier = "";
		String tens = "";
		String and = "";
		String ones  = "";
		
		int tensCol = _val / 10;
		int onesCol = _val - (tensCol * 10);
		
		// Define multiplier
		switch (tensCol) {
			case 2: multiplier = "rua ";
					break;
			case 3: multiplier = "toru ";
					break;
			case 4: multiplier = "whā ";
					break;
			case 5: multiplier = "rima ";
					break;
			case 6: multiplier = "ono ";
					break;
			case 7: multiplier = "whitu ";
					break;
			case 8: multiplier = "waru ";
					break;
			case 9: multiplier = "iwa ";
					break;
		}
		
		// Define tens
		if (tensCol > 0) {
			tens = "tekau ";
		}
		
		// Define and
		if ((onesCol != 0) && (tensCol != 0)) {
			and = "mā ";
		}
		
		// Define ones
		switch (onesCol) {
			case 1: ones = "tahi";
					break;
			case 2: ones = "rua";
					break;
			case 3: ones = "toru";
					break;
			case 4: ones = "whā";
					break;
			case 5: ones = "rima";
					break;
			case 6: ones = "ono";
					break;
			case 7: ones = "whitu";
					break;
			case 8: ones = "waru";
					break;
			case 9: ones = "iwa";
					break;
		}
		
		return (multiplier + tens + and + ones).trim();
	}
}
