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
	 * @param firstName 
	 * @param lastName
	 * @param userName
	 */
	public Teacher(String firstName, String lastName, String userName) {
		super(firstName, lastName, userName);
		_writingPrivleges = true;
	}

	@Override
	public void saveUser() {
		// TODO Auto-generated method stub
		
	}

}
