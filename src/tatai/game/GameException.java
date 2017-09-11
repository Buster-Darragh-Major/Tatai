package tatai.game;


/**
 * An exception for errors in the game class
 * 
 * @author Nathan Cairns
 */
@SuppressWarnings("serial")
public class GameException extends RuntimeException {
	public GameException(String msg) {
		super(msg);
	}
}