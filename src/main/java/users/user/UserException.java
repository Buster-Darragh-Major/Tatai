package main.java.users.user;

/**
 * Exception for User class and inheritors.
 * 
 * @author Nathan Cairns
 *
 */
@SuppressWarnings("serial")
public class UserException extends RuntimeException {
	public UserException(String msg) {
		super(msg);
	}
}
