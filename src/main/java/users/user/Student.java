package main.java.users.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import main.java.game.Level;
import main.java.stats.Stat;
import main.java.stats.StatSkill;
import main.java.stats.TataiStat;

/**
 * This class represents a student user. A student user does not have writing
 * privileges and therefore cannot create custom lists. A student can also have
 * achievments. //TODO
 * 
 * @author Nathan Cairns
 *
 */
public class Student extends User {

	private boolean _level2Unlocked;
	private boolean _level2ReverseUnlocked;

	/**
	 * Constructor
	 * 
	 * @param firstName
	 *            the first name of the user
	 * @param LastName
	 *            the last name of the user
	 * @param userName
	 *            the user name the user has choosen
	 */
	public Student(String firstName, String lastName, String userName) throws UserException {
		super(firstName, lastName, userName);
		lockUser();
	}

	/**
	 * Constructor for Student with pre existing stats
	 */
	public Student(@JsonProperty("_firstName") String firstName, @JsonProperty("_lastName") String lastName,
			@JsonProperty("_userName") String userName, @JsonProperty("_lvl1Stats") TataiStat stat1,
			@JsonProperty("_lvl2Stats") TataiStat stat2, @JsonProperty("_lvl1ReverseStats") TataiStat stat3,
			@JsonProperty("_lvl2ReverseStats") TataiStat stat4, @JsonProperty("_level2Unlocked") boolean level2Unlocked,
			@JsonProperty("_level2ReverseUnlocked") boolean level2ReverseUnlocked) {
		super(firstName, lastName, userName, stat1, stat2, stat3, stat4);
		_writingPrivileges = false;
		_level2Unlocked = level2Unlocked;
		_level2ReverseUnlocked = level2ReverseUnlocked;
	}

	/**
	 * Locks the users priveleges. Students do not start with levels unlocked and
	 * never have writing privileges.
	 */
	private void lockUser() {
		_writingPrivileges = false;
		_level2Unlocked = false;
		_level2ReverseUnlocked = false;
	}

	/**
	 * Determines the users skill level for a given stat and level.
	 * 
	 * @param stat
	 *            the stat to enquire about
	 * @param level
	 *            the game level of the stat you are interested in
	 * @return the skill level of that stat for a given level.
	 */
	public StatSkill getStatSkill(Stat stat, Level level) {
		int value = 0;

		switch (stat) {
		case TOTALPLAYED:
			value = getTotalPlayed(level);
			break;
		case TOTALCORRECT:
			value = getTotalCorrect(level);
			break;
		case TOTALINCORRECT:
			value = getTotalIncorrect(level);
			break;
		case AVERAGE:
			return determineAverageSkill(getAverage(level));
		}
		return determineTotalSkill(value);
	}

	/**
	 * Determines the the stat skill level for the three "total" stats. i.e Total
	 * played, total correct & total incorrect.
	 * 
	 * @param value
	 *            the value of the particular stat.
	 * @return the skill level
	 */
	protected StatSkill determineTotalSkill(int value) {
		if (value >= 25 && value < 50) {
			return StatSkill.BRONZE;
		} else if (value >= 50 && value < 100) {
			return StatSkill.SILVER;
		} else if (value >= 100 && value < 1000) {
			return StatSkill.GOLD;
		} else if (value >= 1000) {
			return StatSkill.PLATINUM;
		} else {
			return StatSkill.NONE;
		}
	}

	/**
	 * Determines the stat skill level for the students average.
	 * 
	 * @param value
	 *            the percent the student's average is currently on
	 * @return the stat skill level of the student's average
	 */
	protected StatSkill determineAverageSkill(double value) {
		if (value >= 6.0 && value < 7.5) {
			return StatSkill.BRONZE;
		} else if (value >= 7.5 && value < 8.5) {
			return StatSkill.SILVER;
		} else if (value >= 8.5 && value < 9.5) {
			return StatSkill.GOLD;
		} else if (value >= 9.5 && value <= 10.0) {
			return StatSkill.PLATINUM;
		} else {
			return StatSkill.NONE;
		}
	}

	@Override
	public void unlockLevel(Level level) {
		switch (level) {
		case Level2:
			if (getPersonalBest(Level.Level1) >= 8) {
				_level2Unlocked = true;
			}
			break;
		default:
			break;
		}

	}

	@Override
	public boolean isUnlocked(Level level) {
		switch (level) {
		case Level2:
			return _level2Unlocked;
		default:
			return true;
		}
	}
	
	@Override
	public void updateStats(int played, int correct, int incorrect, int score, Level level) {
		super.updateStats(played, correct, incorrect, score, level);
		if (level == Level.Level1) {
			unlockLevel(Level.Level2);
		}
	}
}
