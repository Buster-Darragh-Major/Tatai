package main.java.users.classroom;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.users.user.Student;
import main.java.users.user.Teacher;
import main.java.users.user.User;

/**
 * This interface represents a classroom.
 * 
 * @author Nathan Cairns
 *
 */

public class TataiClassRoom implements ClassRoom {
	/* MACROS */
	public static final String TEACHER_ERROR = "No teacher with name found: ";
	public static final String STUDENT_ERROR = "No student with name found: ";

	private enum MemberType {
		TEACHER, STUDENT;
	}

	private Set<User> _students;
	private Set<User> _teachers;

	public TataiClassRoom() {
		_students = new HashSet<User>();
		_teachers = new HashSet<User>();
		readRoll();
	}

	/**
	 * if no student is with user name found exception is thrown
	 */
	@Override
	public Student getStudentByUsername(String userName) throws ClassRoomException {
		return (Student) byUserName(userName, MemberType.STUDENT);
	}

	/**
	 * if no students were found with name exceptions is thrown
	 */
	@Override
	public List<User> getStudentsByName(String name) throws ClassRoomException {
		return byName(name, MemberType.STUDENT);
	}

	/**
	 * if no teachers were found with user name exception is thrown
	 */
	@Override
	public Teacher getTeacherByUsername(String userName) throws ClassRoomException {
		return (Teacher) byUserName(userName, MemberType.TEACHER);
	}

	/**
	 * if no teacher is found with name exception is thrown
	 */
	@Override
	public List<User> getTeachersByName(String name) throws ClassRoomException{
		return byName(name, MemberType.TEACHER);
	}
	
	@Override
	public void addStudent(Student student) throws ClassRoomException {
		if (containsUser(student.userName())) {
			throw new ClassRoomException("user with username " + student.userName() + " is already in classroom");
		}
		_students.add(student);
	}

	@Override
	public void addTeacher(Teacher teacher) throws ClassRoomException{
		if (containsUser(teacher.userName())) {
			throw new ClassRoomException("user with username " + teacher.userName() + " is already in classroom");
		}
		_teachers.add(teacher);
	}
	
	/**
	 * Searches for a user by Username
	 * 
	 * @param userName the user to search for's username
	 * @param type the type of user
	 * @return the User if they were found
	 */
	private User byUserName(String userName, MemberType type) throws ClassRoomException {
		Set<User> searchSpace = null;
		String error = null;
		
		switch(type) {
		case STUDENT:
			searchSpace = _students;
			error = STUDENT_ERROR;
			break;
		case TEACHER:
			searchSpace = _teachers;
			error = TEACHER_ERROR;
			break;
		}
		
		for (User user: searchSpace) {
			if (user.userName().equals(userName)) {
				return user;
			}
		}
		throw new ClassRoomException(error);
	}

	/**
	 * Searches for a user by name.
	 * 
	 * @param name
	 *            the user's name to search by.
	 * @param type
	 *            the type of user to search for.
	 * @return a list of users with matching names
	 */
	private List<User> byName(String name, MemberType type) throws ClassRoomException {
		List<User> found = new ArrayList<User>();
		Set<User> searchSpace = null;
		String error = null;

		switch (type) {
		case TEACHER:
			searchSpace = _teachers;
			error = TEACHER_ERROR;
			break;
		case STUDENT:
			searchSpace = _students;
			error = STUDENT_ERROR;
			break;
		}

		for (User user : searchSpace) {
			String firstName = user.firstName().toLowerCase();
			String lastName = user.lastName().toLowerCase();
			String fullName = user.name().toLowerCase();
			String nameLower = name.toLowerCase();

			if (firstName.equals(nameLower) || lastName.equals(nameLower) || fullName.equals(nameLower)) {
				found.add(user);
			}
		}

		if (found.size() > 0) {
			return found;
		} else {
			throw new ClassRoomException(error);
		}
	}
	
	/**
	 * Save the roll
	 */
	@Override
	public void saveRoll() {
		for (User user : _students) {
			user.saveUser();
		}
		
		for (User user : _teachers) {
			user.saveUser();
		}
	}

	/**
	 * Read the roll
	 */
	@Override
	public void readRoll() {
		User.createUsersFolder();
		File[] userFiles = User.USER_DIR.listFiles();
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enableDefaultTyping(
			    ObjectMapper.DefaultTyping.NON_CONCRETE_AND_ARRAYS); 
		
		for (File f : userFiles) {
			try {
				User user = mapper.readValue(f, User.class);
				
				if (user instanceof Teacher) {
					addTeacher((Teacher) user);
				} else {
					addStudent((Student) user);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<String> listTeachers() {
		List<String> teacherUserNames = new ArrayList<String>();
		
		for (User user : _teachers) {
			teacherUserNames.add("" + user);
		}
		
		return teacherUserNames;
	}

	@Override
	public List<String> listStudents() {
		List<String> studentUserNames = new ArrayList<String>();
		
		for (User user : _students) {
			studentUserNames.add("" + user);
		}
		return studentUserNames;
	}

	@Override
	public void removeTeacher(String userName) {
		User user = getTeacherByUsername(userName);
		_teachers.remove(user);
		deleteFile(user._userFile);
	}

	@Override
	public void removeStudent(String userName) {
		User user = getStudentByUsername(userName);
		_students.remove(user);
		deleteFile(user._userFile);
	}
	
	private void deleteFile(File file) {
		try {
			Files.delete(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean containsUser(String username) {
		for (User user : _students ) {
			if (user.userName().equals(username)) {
				return true;
			}
		}
		
		for (User user : _teachers) {
			if (user.userName().equals(username)) {
				return true;
			}
		}
		
		return false;
	}


}
