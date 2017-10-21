package main.java.users.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import main.java.stats.TataiStat;

/**
 * This class represents a teacher user. A teacher user is like a normal user
 * except it has writing privileges, allowing them to create custom lists.
 * 
 * @author Nathan Cairns
 *
 */
public class Teacher extends User {

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
	public Teacher(String firstName, String lastName, String userName) throws UserException {
		super(firstName, lastName, userName);
		_writingPrivileges = true;
	}

	/**
	 * Constructor for teacher with pre existing stats
	 */
	public Teacher(@JsonProperty("_firstName") String firstName, @JsonProperty("_lastName") String lastName,
			@JsonProperty("_userName") String userName, @JsonProperty("_lvl1Stats") TataiStat stat1,
			@JsonProperty("_lvl2Stats") TataiStat stat2) {
		super(firstName, lastName, userName, stat1, stat2);
		_writingPrivileges = true;
	}
}
