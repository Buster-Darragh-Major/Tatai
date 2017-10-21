package main.java.game;

public class TataiGameReverse extends TataiGame {
	
	public TataiGameReverse() {
		super();
	}
	
	/**
	 * Returns a header for the current set level difficulty.
	 * 
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
	 * 
	 * @return String : description
	 */
	public String getLevelDescription() {
		if (_level == Level.Level1) {
			return ("See and hear the number in Māori, then type it! Answers range from 1-9");
		} else if (_level == Level.Level2) {
			return ("See and hear the number in Māori, then type it! Answers range from 1-99");
		}
		return null;
	}
	
	/**
	 * Answer the current question
	 */
	public boolean answerQuestion(boolean answer) {
		if (answer) {
			_questionsCorrect.add("Correct");
			_correct++;
			nextQuestion();
			return true;
		} else {
			_questionsCorrect.add("Incorrect");
			_incorrect++;
			nextQuestion();
			return false;
		}
	}
	
	/**
	 * End the game
	 */
	public void endGame() {
		if (_hasStarted) {
			_currentUser.saveUser();
			_hasStarted = false;
		} else {
			throw new GameException("Game has already ended");
		}
	}
}
