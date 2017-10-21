package gui;

import game.TataiGame;
import game.TataiGameEquation;
import game.TataiGameReverse;

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
	private TataiGame _regularGame;
	private TataiGameEquation _equationGame;
	private TataiGameReverse _reverseGame;
	private String _equationList;
	
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
		_regularGame = new TataiGame();
		_equationGame = new TataiGameEquation();
		
		// By default set to return a regular game type
		_game = _regularGame;
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
	
	/**
	 * Sets the current game type to be an equation based game.
	 */
	public void setGameToEquation() {
		_game = _equationGame;
	}
	
	/**
	 * sets the current game type to be a desired type game;
	 */
	public void setGameType(TataiGame game) {
		_game = game;
	}
	
	/**
	 * Sets the current game type to be a regular game.
	 */
	public void setGameToRegular() {
		_game = _regularGame;
	}
	
	/**
	 * Sets the current equation list name that Context cares about to be that of input string 
	 * @param listName : String
	 */
	public void setQuestionList(String listName) {
		_equationList = listName;
	}
	
	/**
	 * Returns the current list name that Context tracks
	 * @return equationList : String
	 */
	public String currentQuestionList() {
		return _equationList;
	}
}
