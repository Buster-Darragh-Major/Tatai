package tatai.game;

import tatai.creations.Level;

public class TataiGamePractice extends TataiGame {
	
	
	
	@Override 
	public void setLevel(Level level) {
		throw new GameException("You cant set the level on the tutorial.");
	}
	
	@Override
	public int currentQuestion() {
		throw new GameException("");
	}
	
	
}
