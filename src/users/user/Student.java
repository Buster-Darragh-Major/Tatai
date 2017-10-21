package users.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import game.Level;
import stats.Stat;
import stats.StatSkill;
import stats.TataiStat;

/**
 * This class represents a student user. A student user does not have writing
 * privileges and therefore cannot create custom lists. A student can also have
 * achievments. //TODO
 * 
 * @author Nathan Cairns
 *
 */
public class Student extends User {

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
		_writingPrivileges = false;
	}

	/**
	 * Constructor for Student with pre existing stats
	 */
	public Student(@JsonProperty("_firstName") String firstName, @JsonProperty("_lastName") String lastName,
			@JsonProperty("_userName") String userName, @JsonProperty("_lvl1Stats") TataiStat stat1,
			@JsonProperty("_lvl2Stats") TataiStat stat2) {
		super(firstName, lastName, userName, stat1, stat2);
		_writingPrivileges = false;
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

}
