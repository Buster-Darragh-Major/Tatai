package main.java.stats;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents the statistics for a given game mode.
 * 
 * @author Nathan Cairns
 *
 */
public class TataiStat implements StatisticHandler {
	private int _totalPlayed;
	private int _totalCorrect;
	private int _totalIncorrect;
	private int _personalBest;

	public TataiStat() {
		this(0, 0, 0,0);
	}
	
	/**
	 * Existing statistic constructor
	 * @param totalPlayed
	 * @param totalCorrect
	 * @param totalIncorrect
	 * @param personalBest
	 */
	public TataiStat(@JsonProperty("_totalPlayed")int totalPlayed,@JsonProperty("_totalCorrect") int totalCorrect, @JsonProperty("_totalIncorrect") int totalIncorrect, @JsonProperty("_personalBest") int personalBest) {
		_totalPlayed = totalPlayed;
		_totalCorrect = totalCorrect;
		_totalIncorrect = totalIncorrect;
		_personalBest = personalBest;
	}

	@Override
	public int totalPlayed() {
		return _totalPlayed;
	}

	@Override
	public int totalIncorrect() {
		return _totalIncorrect;
	}

	@Override
	public int totalCorrect() {
		return _totalCorrect;
	}

	@Override
	public double average() {
		return calculateAverage();
	}

	/**
	 * Calculate the average score from games the played and question correct.
	 * 
	 * @return the average
	 */
	private double calculateAverage() {

		if (totalPlayed() == 0) {
			return 0;
		}

		double average = (double) totalCorrect() / totalPlayed() * 10;

		average = Double.parseDouble(String.format("%.2f", average));

		return average;
	}

	@Override
	public void updateStats(int played, int correct, int incorrect, int personalBest) {
		if (played > 10) {
			throw new StatsException("You can not have answered more than 10 questions in a session");
		}

		if (played < 0 || correct < 0 || incorrect < 0) {
			throw new StatsException("Scores cannot be negative!");
		}
		if ((correct + incorrect) != played) {
			throw new StatsException("The number of question you got correct and the number of questions you "
					+ "got wrong must be consistent with the number of questions you answered");
		}

		_totalPlayed += played;
		_totalCorrect += correct;
		_totalIncorrect += incorrect;
		
		if (personalBest > _personalBest) {
			_personalBest = personalBest;
		}
	}

	@Override
	public int personalBest() {
		return _personalBest;
	}

}
