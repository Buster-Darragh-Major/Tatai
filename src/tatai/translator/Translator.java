package tatai.translator;

/**
 * An interface for translating strings to other langauages
 * 
 * @author Nathan Cairns
 *
 */
public interface Translator {
	
	/**
	 * Takes a string and translates it to another language.
	 * 
	 * @param The string to be translated
	 * @return The translated String
	 */
	public String translate(String toTranslate);
}
