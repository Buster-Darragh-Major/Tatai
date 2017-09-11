package gui;

import tatai.game.TataiGame;

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
	
	// Objects singleton is keeping track of
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
	 * Creates a reference in singleton Context object to an already existing 
	 * TataiGame object
	 */
	public void newGame(TataiGame game) {
		_game = game;
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
