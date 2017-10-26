package main.java.users.user;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.game.Level;
import main.java.stats.StatisticHandler;
import main.java.stats.TataiPaths;
import main.java.stats.TataiStat;

/**
 * Abstract class representing a user.
 * 
 * @author Nathan Cairns
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Student.class, name = "Student"),

    @JsonSubTypes.Type(value = Teacher.class, name = "Teacher") }
)
public abstract class User {
	/* MACROS */
	public static final File USER_DIR = new File(TataiPaths.DATA_DIR + System.getProperty("file.separator") + "users");

	@JsonIgnore public final File _userFile;
	@JsonIgnore protected boolean _writingPrivileges;
	protected String _firstName;
	protected String _lastName;
	protected String _userName;
	protected StatisticHandler _lvl1Stats;
	protected StatisticHandler _lvl2Stats;
	protected StatisticHandler _lvl1ReverseStats;
	protected StatisticHandler _lvl2ReverseStats;

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
	public User(String firstName, String LastName, String userName) throws UserException {
		_firstName = firstName.trim();
		_lastName = LastName.trim();
		_userName = userName.trim();
		
		if (_firstName == null || _lastName == null || _userName == null) {
			throw new UserException("All name fields must be provided");
		}
		
		if (_firstName == "" || _firstName.matches(".*\\d+.*") || _firstName.contains(" ")) {
			throw new UserException("Invalid first name: " + _firstName + " choosen for user. Please make sure the name field is filled in and there are no "
					+ "spaces or digits");
		}
		if (_lastName == "" || _firstName.matches(".*\\d+.*") || _lastName.contains(" ")) {
			throw new UserException("Invalid last name: " + _lastName + " choosen for user. Please make sure the name field is filled in and there are no "
					+ "spaces or digits");
		}
		if (_userName == "") {
			throw new UserException("Invalid username: " + _userName + " choosen for user. Please make sure this is not empty.");
		}
		
		_lvl1Stats = new TataiStat();
		_lvl2Stats = new TataiStat();
		_lvl1ReverseStats = new TataiStat();
		_lvl2ReverseStats = new TataiStat();

		_userFile = new File(USER_DIR + System.getProperty("file.separator") + userName);
	}
	
	/**
	 * Constructor for user with pre existing stats
	 * @param firstName first name of the user
	 * @param lastName last name of the user
	 * @param userName username the user has choosen
	 * @param stat1 their lvl1 stats
	 * @param stat2
	 */
	public User(String firstName, String lastName, String userName, TataiStat stat1, TataiStat stat2, TataiStat stat3, TataiStat stat4) {
		this(firstName, lastName, userName);
		_lvl1Stats = stat1;
		_lvl2Stats = stat2;
		_lvl1ReverseStats = stat3;
		_lvl2ReverseStats = stat4;
	}
	
	public abstract void unlockLevel(Level level);
	
	public abstract boolean isUnlocked(Level level);

	/**
	 * Saves the user. Inheritors should decide how a user is saved.
	 */
	public void saveUser() {
		String jsonStr = toJsonString();

		writeToFile(jsonStr);
	}

	/**
	 * Converts this object to a Json string Convenience method for inheritors to
	 * use.
	 * 
	 * @return object as Json String
	 */
	protected String toJsonString() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		String jsonStr = null;
		try {
			jsonStr = mapper.writeValueAsString(this);
			return jsonStr;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	protected void writeToFile(String jsonStr) {
		createUsersFolder();

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(_userFile.getAbsolutePath(), false));
			writer.write(jsonStr);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Makes the appropriate directory if it doesn't already exist.
	 */
	public static void createUsersFolder() {
		if (!USER_DIR.exists()) {
			USER_DIR.mkdirs();
		}
	}

	/**
	 * Checks if the user has writing privileges
	 * 
	 * @return writingPrivileges
	 */
	public boolean hasWritingPrivileges() {
		return _writingPrivileges;
	}

	/**
	 * Returns the user's username
	 * 
	 * @return username
	 */
	public String userName() {
		return _userName;
	}

	/**
	 * Returns the user's first name
	 * 
	 * @return first name
	 */
	public String firstName() {
		return _firstName;
	}

	/**
	 * Returns the user's last name;
	 * 
	 * @return last name
	 */
	public String lastName() {
		return _lastName;
	}

	/**
	 * Returns the user's full name
	 * 
	 * @return full name
	 */
	public String name() {
		return _firstName + " " + _lastName;
	}

	/**
	 * Gets the total played for the appropriate level.
	 * 
	 * @param l
	 *            the level
	 * @return total player for l
	 */
	public int getTotalPlayed(Level l) {
		StatisticHandler ts = determineStatLevel(l);

		return ts.totalPlayed();
	}

	/**
	 * Gets the total correct for the appropriate level
	 * 
	 * @param l
	 *            the level
	 * @return total correct for l
	 */
	public int getTotalCorrect(Level l) {
		StatisticHandler ts = determineStatLevel(l);

		return ts.totalCorrect();
	}

	/**
	 * Gets the total incorrect for the appropriate level
	 * 
	 * @param l
	 *            the level
	 * @return total incorrect for l
	 */
	public int getTotalIncorrect(Level l) {
		StatisticHandler ts = determineStatLevel(l);

		return ts.totalIncorrect();
	}

	/**
	 * Gets the average for the appropriate level
	 * 
	 * @param l
	 *            the level
	 * @return average for l
	 */
	public double getAverage(Level l) {
		StatisticHandler ts = determineStatLevel(l);

		return ts.average();
	}
	
	public int getPersonalBest(Level l) {
		StatisticHandler ts = determineStatLevel(l);
		
		return ts.personalBest();
	}
	
	/**
	 * Updates the users stats for a specific level
	 * @param played amount just played
	 * @param correct amount correct
	 * @param incorrect amount incorrect
	 * @param level the level to add stats to
	 */
	public void updateStats(int played, int correct, int incorrect, int score, Level level) {
		StatisticHandler ts = determineStatLevel(level);
		
		ts.updateStats(played, correct, incorrect, score);
	}

	/**
	 * Determines which level stats to use.
	 * 
	 * @param l
	 *            the level
	 * @return the stats of the apporpriate level
	 */
	protected StatisticHandler determineStatLevel(Level l) {
		StatisticHandler ts = null;

		switch (l) {
		case LEVEL1:
			ts = _lvl1Stats;
			break;
		case LEVEL2:
			ts = _lvl2Stats;
			break;
		case LEVEL1_REVERSE:
			ts = _lvl1ReverseStats;
			break;
		case LEVEL2_REVERSE:
			ts = _lvl2ReverseStats;
			break;
		default:
			break;
		}
		return ts;
	}

	/**
	 * Overrides the toString method to return the user's username
	 */
	@Override
	public String toString() {
		return _userName;
	}

	/**
	 * Overrides the equals method so that equality between users is determined by
	 * their username.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof User)) {
			return false;
		}

		User user = (User) o;

		return this.userName().equals(user.userName());
	}

}
