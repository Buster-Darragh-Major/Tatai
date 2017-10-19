package user.classroom;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import users.user.Student;
import users.user.Teacher;

/**
 * This interface represents a classroom.
 * 
 * @author Nathan Cairns
 *
 */
public class TataiClassRoom implements ClassRoom {
	private Set<Student> _students;
	private Set<Teacher> _teachers;
	
	public TataiClassRoom() {
		_students = new HashSet<Student>();
		_teachers = new HashSet<Teacher>();
	}

	@Override
	public Student getStudentByUsername(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getStudentsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Teacher getTeacherByUsername(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Teacher> getTeachersByName(String Name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addStudent(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		
	}
	
}
