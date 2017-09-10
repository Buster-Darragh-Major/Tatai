package tatai.game;

import tatai.creations.Level;

/**
 * Class that deals with the abstract parameters of the game itself, i.e. the current
 * game state. This concerns things like current game difficulty level, any text 
 * relevant to a game / game level, amount of questions answered, amount correct, 
 * incorrect, references to the CreationModel storing the answered/unanswered 
 * creations, etc.
 * 
 * @author Buster Major
 */
public class TataiGame {
	
	private Level _level = Level.Level1;
	private int _questionNo;
	
	/**
	 * Constructor
	 */
	public TataiGame() {
		_questionNo = 1;
	}
	
	/**
	 * Sets the current level difficulty for the game object.
	 * @param level
	 */
	public void setLevel(Level level) {
		_level = level;
	}
	
	/**
	 * Gets the current level difficulty for the game object.
	 * @returns Level : level
	 */
	public Level getLevel() {
		return _level;
	}
	
	/**
	 * Gets the current question number for the game object
	 * 
	 * @return int : Question Number
	 */
	public int getQuestionNo() {
		return _questionNo;
	}
	
	/**
	 * Increments question number for next question in quiz
	 */
	public void nextQuestion() {
		_questionNo++;
	}
	
	/**
	 * Returns a header for the current set level difficulty.
	 * @return String : header
	 */
	public String getLevelHeader() {
		if (_level == Level.Level1) {
			return ("Level 1");
		} else if (_level == Level.Level2) {
			return ("Level 2");
		}
		return null;
	}
	
	/**
	 * Returns a description of the current set level difficulty.
	 * @return String : description
	 */
	public String getLevelDescription() {
		if (_level == Level.Level1) {
			return ("Ten questions ranging from numbers 1-10");
		} else if (_level == Level.Level2) {
			return ("Ten questions ranging from numbers 1-99");
		}
		return null;
	}
}
