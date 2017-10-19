package users.user;

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

import stats.TataiHandler;
import stats.TataiStat;
import tatai.creations.Level;

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
public abstract class User extends TataiHandler {
	/* MACROS */
	public static final File USER_DIR = new File(DATA_DIR + System.getProperty("file.separator") + "users");

	@JsonIgnore protected File _userFile;
	@JsonIgnore protected boolean _writingPrivileges;
	protected String _firstName;
	protected String _lastName;
	protected String _userName;
	protected TataiStat _lvl1Stats;
	protected TataiStat _lvl2Stats;

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
	public User(String firstName, String LastName, String userName) {
		_firstName = firstName;
		_lastName = LastName;
		_userName = userName;
		_lvl1Stats = new TataiStat();
		_lvl2Stats = new TataiStat();

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
	public User(String firstName, String lastName, String userName, TataiStat stat1, TataiStat stat2) {
		this(firstName, lastName, userName);
		_lvl1Stats = stat1;
		_lvl2Stats = stat2;
	}

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
		TataiStat ts = determineStatLevel(l);

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
		TataiStat ts = determineStatLevel(l);

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
		TataiStat ts = determineStatLevel(l);

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
		TataiStat ts = determineStatLevel(l);

		return ts.average();
	}

	/**
	 * Determines which level stats to use.
	 * 
	 * @param l
	 *            the level
	 * @return the stats of the apporpriate level
	 */
	private TataiStat determineStatLevel(Level l) {
		TataiStat ts = null;

		switch (l) {
		case Level1:
			ts = _lvl1Stats;
			break;
		case Level2:
			ts = _lvl2Stats;
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
