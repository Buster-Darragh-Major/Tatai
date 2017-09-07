package gui;

import Game.TataiGame;

/**
 * Singleton class concerned with the 'context' of the program running, i.e. the
 * current state. Holds references to objects such as the current game object. This
 * class is accessible anywhere and hence can be used by multiple controllers to
 * determine what to display.
 * 
 * @author Buster Major
 *
 */
public class Context {
	// Private and static constructor, singleton instance
	private final static Context instance = new Context();
	
	private TataiGame _game;
	
	/**
	 * Returns instance of singleton object
	 * @return Context : instance
	 */
	public static Context getInstance() {
		return instance;
	}
	
	/**
	 * Creates a new TataiGame object for a new game in play.
	 */
	public void newGame() {
		_game = new TataiGame();
	}
	
	/**
	 * Returns the current game object stored in the singleton instance,
	 * this corresponds to the current game in play.
	 * @return TataiGame : game
	 */
	public TataiGame currentGame() {
		return _game;
	}
}
