package stats;

/**
 * Interface to represent a type which returns statistics
 * 
 * @author Nathan Cairns
 *
 */
public interface StatisticHandler {

	public int totalPlayer();
	
	public int totalIncorrect();
	
	public int totalCorrect();
	
	public int average();
	
	public int update(int played, int incorrect, int correct);
}
