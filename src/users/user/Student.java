package users.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import stats.TataiStat;

/**
 * This class represents a student user.
 * A student user does not have writing privileges and therefore cannot
 * create custom lists.
 * A student can also have achievments. //TODO
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
	

	/**
	 * Constructor for Student with pre existing stats
	 */
	public Student(@JsonProperty("_firstName")String firstName, @JsonProperty("_lastName") String lastName,
			@JsonProperty("_userName")String userName, @JsonProperty("_lvl1Stats") TataiStat stat1,
			@JsonProperty("_lvl2Stats") TataiStat stat2) {
		super(firstName, lastName, userName, stat1, stat2);
		_writingPrivileges = false;
	}
}
