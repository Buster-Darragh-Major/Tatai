package users.user;

/**
 * This class represents a student user.
 * A student user does not have writing privileges and therefore cannot
 * create custom lists.
 * A student can also have achievments.
 * 
 * @author Nathan Cairns
 *
 */
public class Student extends User {
	
	
	/**
	 * Constructor
	 * 
	 * @param firstName the first name of the user
	 * @param LastName the last name of the user
	 * @param userName the user name the user has choosen
	 */
	public Student(String firstName, String lastName, String userName) {
		super(firstName, lastName, userName);
		_writingPrivileges = false;
	}
	@Override
	public void saveUser() {
		// TODO Auto-generated method stub

	}

}