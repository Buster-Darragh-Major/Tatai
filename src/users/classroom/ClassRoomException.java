package users.classroom;

/**
 * An exception for errors in to do with ClassRooms;
 * 
 * @author Nathan Cairns
 *
 */
@SuppressWarnings("serial")
public class ClassRoomException extends RuntimeException {
	public ClassRoomException(String msg) {
		super(msg);
	}
}
