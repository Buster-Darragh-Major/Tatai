package users.classroom;

import java.util.List;

import users.user.Student;
import users.user.Teacher;
import users.user.User;

/**
 * Interface representing a classroom with taechers and students.
 * 
 * @author Nathan Cairns
 *
 */

public interface ClassRoom {

	/**
	 * Get a student by their username
	 * 
	 * @param userName
	 *            student's user name
	 * @return student that was searched for
	 */
	public Student getStudentByUsername(String userName);

	/**
	 * Get a student by their name
	 * 
	 * @param name
	 *            the student's name
	 * @return Student that was searched for
	 */
	public List<User> getStudentsByName(String name);

	/**
	 * Get a teacher by their username
	 * 
	 * @param userName
	 *            the teacher's username
	 * @return Teacher that was searched for
	 */
	public Teacher getTeacherByUsername(String userName);

	/**
	 * Get a teacher by their name
	 * 
	 * @param Name
	 *            the teacher's name
	 * @return Teacher that was searched for
	 */
	public List<User> getTeachersByName(String Name);

	/**
	 * Adds a student to the classroom It is up to implementers of this interface to
	 * decide how many students a classroom can have.
	 * 
	 * @param student
	 *            the student to be added
	 */
	public void addStudent(Student student);

	/**
	 * Adds a teacher to the classroom. It is up to implementers of this interface
	 * to decide on how many teachers a classroom should have.
	 * 
	 * @param teacher
	 */
	public void addTeacher(Teacher teacher);

	/**
	 * Removes a teacher by userName
	 * 
	 * @param userName
	 *            the teacher to be removeds username
	 */
	public void removeTeacher(String userName);

	/**
	 * Removes a student by userName
	 * 
	 * @param userName
	 *            the teach to be removeds username
	 */
	public void removeStudent(String userName);

	/**
	 * Return a list of teachers
	 * 
	 * @return the teachers in the class
	 */
	public List<String> listTeachers();

	/**
	 * Return a list of the students
	 * 
	 * @return the students in the class
	 */
	public List<String> listStudents();

	/**
	 * Save the students and teachers
	 */
	public void saveRoll();

	/**
	 * Read the roll from wherever it is saved
	 */
	public void readRoll();
}
