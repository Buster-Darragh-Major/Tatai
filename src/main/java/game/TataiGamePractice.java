package main.java.game;

public class TataiGamePractice extends TataiGame {
	
	
	
	@Override 
	public void setLevel(Level level) {
		throw new GameException("You cant set the level on the tutorial.");
	}
	
	@Override
	public int currentQuestion() {
		throw new GameException("There are no question numbers in practice mode");
	}
	
	
}
