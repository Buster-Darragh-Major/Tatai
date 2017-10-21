package main.java.stats;

/**
 * Simple interface which represents a type which returns 
 * statistics based on a game.
 * 
 * @author Nathan Cairns
 *
 */
public interface StatisticHandler {

	/**
	 * @return The total number of questions answered
	 */
	public int totalPlayed();
	
	/**
	 * @return The total number of questions that were incorrect.
	 */
	public int totalIncorrect();
	
	/**
	 * @return The total number of questions that were correct.
	 */
	public int totalCorrect();
	
	/**
	 * @return The average score
	 */
	public double average();
	
	/**
	 * Update the players statistics
	 * 
	 * @param played questions answered this session
	 * @param incorrect questions incorrectly answered this session
	 * @param correct questions correctly answered this session
	 * 
	 */
	public void updateStats(int played, int correct, int incorrect);
}
