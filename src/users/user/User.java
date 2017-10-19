package users.user;

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
