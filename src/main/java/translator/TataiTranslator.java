package main.java.translator;

/**
 * This class represents the ability to translate integers to strings
 * representing the relevant integer in Maori.
 * 
 * Maori counting is structured fairly simply from 1 to 99. We will consider any
 * number in this range to have 4 distinct components. A "multiplier", first in
 * the string telling the value of tens column there are, followed by "tens", in
 * this case represented by just "tekau", followed by "and", in this case "ma"
 * and finally the value of the ones column, "ones". For example: 84 =
 * iwa-("multiplier") tekau-("tens") mā-("and") whā-("ones") iwa tekau mā whā
 * 
 * @author Buster Major
 * @author Nathan Cairns
 */
public class TataiTranslator implements Translator {
	
	/**
	 * Translates the given string to the maori equivalent.
	 * String must represent an integer from 1 to 99.
	 */
	@Override
	public String translate(String toTranslate) {
		int val;
		
		// Check to see if string parsed represents an int
		try {
			val = Integer.parseInt(toTranslate);
		} catch (NumberFormatException e) {
			throw new TranslatorException("Parameter must be a number");
		}
		
		// Make sure int is within range
		if (val < 1 || val > 99) {
			throw new TranslatorException("Parameter must be a number between 1 and 99");
		}
		
		// Construct the string
		String multiplier = defineMultiplier(val);
		String tens = defineTens(val);
		String and = defineAnd(val);
		String ones = defineOnes(val);

		return (multiplier + tens + and + ones).trim();

	}
	
	/**
	 * Get the ones column of a given integer
	 *  
	 * @param val the given integer
	 * 
	 * @return the ones column
	 */
	private int onesCol(int val) {
		int tensCol = tensCol(val);
		return val - (tensCol * 10);
	}

	/**
	 * Get the tens column of a given integer
	 * 
	 * @param val the given integer
	 * 
	 * @return the tens column
	 */
	private int tensCol(int val) {
		return val / 10;
	}
	
	/**
	 * Define and
	 * 
	 * @param val value to define and for
	 * @return Maori String representing the and component
	 */
	private String defineAnd(int val) {
		int onesCol = onesCol(val);
		int tensCol = tensCol(val);
		String and = "";

		// Define and
		if ((onesCol != 0) && (tensCol != 0)) {
			and = "mā ";
		}

		return and;
	}
	
	/**
	 * Define ones
	 * 
	 * @param val value to define the ones for
	 * 
	 * @return Maori String representation of the the ones column
	 */
	private String defineOnes(int val) {
		int onesCol = onesCol(val);

		String ones = "";
		// Define ones
		switch (onesCol) {
		case 1:
			ones = "tahi";
			break;
		case 2:
			ones = "rua";
			break;
		case 3:
			ones = "toru";
			break;
		case 4:
			ones = "whā";
			break;
		case 5:
			ones = "rima";
			break;
		case 6:
			ones = "ono";
			break;
		case 7:
			ones = "whitu";
			break;
		case 8:
			ones = "waru";
			break;
		case 9:
			ones = "iwa";
			break;
		}

		return ones;
	}
	
	/**
	 * Define the multiplier for a given value
	 * 
	 * @param val value to find the multiplier for
	 * @return Maori String representation of the multiplier
	 */
	private String defineMultiplier(int val) {

		int tensCol = tensCol(val);

		String multiplier = "";

		// Define multiplier
		switch (tensCol) {
		case 2:
			multiplier = "rua ";
			break;
		case 3:
			multiplier = "toru ";
			break;
		case 4:
			multiplier = "whā ";
			break;
		case 5:
			multiplier = "rima ";
			break;
		case 6:
			multiplier = "ono ";
			break;
		case 7:
			multiplier = "whitu ";
			break;
		case 8:
			multiplier = "waru ";
			break;
		case 9:
			multiplier = "iwa ";
			break;
		}
		return multiplier;
	}

	/**
	 * Define Tens
	 * 
	 * @param val value to define tens for
	 * @return Maori String representation of the tens
	 */
	private String defineTens(int val) {
		String tens = "";
		int tensCol = tensCol(val);

		// Define tens
		if (tensCol > 0) {
			tens = "tekau ";
		}

		return tens;
	}
}
