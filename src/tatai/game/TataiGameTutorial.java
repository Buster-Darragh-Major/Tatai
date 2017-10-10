package tatai.game;

import tatai.creations.Level;

public class TataiGameTutorial extends TataiGame {

	@Override 
	public void setLevel(Level level) {
		throw new GameException("You cant set the level on the tutorial.");
	}
	
}
