package tatai.creations;

/**
 * An exception for errors in the translator class
 * 
 * @author Buster Major
 */
@SuppressWarnings("serial")
public class TranslatorException extends RuntimeException {
	public TranslatorException(String msg) {
		super(msg);
	}
}
