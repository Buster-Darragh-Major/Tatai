package users.user;

/**
 * This class represents a teacher user.
 * A teacher user is like a normal user except it has writing privileges,
 * allowing them to create custom lists.
 * 
 * @author Nathan Cairns
 *
 */
public class Teacher extends User{

	/**
	 * Constructor
	 * 
	 * @param firstName the first name of the user
	 * @param LastName the last name of the user
	 * @param userName the user name the user has choosen
	 */
	public Teacher(String firstName, String lastName, String userName) {
		super(firstName, lastName, userName);
		_writingPrivileges = true;
	}
}
