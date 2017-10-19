package users.user;

import res.stats.TataiStat;
import tatai.creations.Level;

/**
 * Abstract class representing a user.
 * 
 * @author Nathan Cairns
 *
 */
public abstract class User {
	protected boolean _writingPrivileges;
	protected String _firstName;
	protected String _lastName;
	protected String _userName;
	
	protected TataiStat _lvl1Stats;
	protected TataiStat _lvl2Stats;
	
	/**
	 * Constructor
	 * 
	 * @param firstName the first name of the user
	 * @param LastName the last name of the user
	 * @param userName the user name the user has choosen
	 */
	public User(String firstName, String LastName, String userName) {
		_firstName = firstName;
		_lastName = LastName;
		_userName = userName;
		_lvl1Stats = new TataiStat();
		_lvl2Stats = new TataiStat();
	}
	
	/**
	 * Saves the user. Inheritors should decide how a user is saved.
	 */
	public abstract void saveUser();
	
	/**
	 * Checks if the user has writing privileges 
	 * @return writingPrivileges
	 */
	public boolean hasWritingPrivileges() {
		return _writingPrivileges;
	}
	
	/**
	 * Returns the user's username
	 * @return username
	 */
	public String userName() {
		return _userName;
	}
	
	/**
	 * Returns the user's first name
	 * @return first name
	 */
	public String firstName() {
		return _firstName;
	}
	
	/**
	 * Returns the user's last name;
	 * @return last name
	 */
	public String lastName() {
		return _lastName;
	}
	
	/**
	 * Returns the user's full name
	 * @return full name
	 */
	public String name() {
		return _firstName + " " + _lastName;
	}
	
	/**
	 * Gets the total played for the appropriate level.
	 * @param l the level
	 * @return total player for l
	 */
	public int getTotalPlayed(Level l) {
		TataiStat ts = determineStatLevel(l);
		
		return ts.totalPlayed();
	}
	
	/**
	 * Gets the total correct for the appropriate level
	 * @param l the level
	 * @return total correct for l
	 */
	public int getTotalCorrect(Level l) {
		TataiStat ts = determineStatLevel(l);
		
		return ts.totalCorrect();
	}
	
	/**
	 * Gets the total incorrect for the appropriate level
	 * @param l the level
	 * @return total incorrect for l
	 */
	public int getTotalIncorrect(Level l) {
		TataiStat ts = determineStatLevel(l);
		
		return ts.totalIncorrect();
	}
	
	/**
	 * Gets the average for the appropriate level
	 * @param l the level
	 * @return average for l
	 */
	public double getAverage(Level l) {
		TataiStat ts = determineStatLevel(l);
		
		return ts.average();
	}
	
	/**
	 * Determines which level stats to use.
	 * @param l the level
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
	 * Overrides the equals method so that equality between users is determined
	 * by their username.
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
