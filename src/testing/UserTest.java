package testing;

import org.junit.Before;
import org.junit.Test;

import user.classroom.ClassRoom;
import user.classroom.TataiClassRoom;
import users.user.Student;
import users.user.Teacher;

public class UserTest {
	Student _student;
	Student _student1;
	Student _student2;
	Student _student3;
	Student _student4;
	
	Teacher _teacher1;
	Teacher _teacher2;
	
	ClassRoom c = new TataiClassRoom();
	
	@Before
	public void setup() {
		_student = new Student("Barry", "Bonds", "bboy");
		_student1 = new Student("Harry", "Wilson", "hboy");
		_student2 = new Student("Johnson", "Stevens", "stevatron");
		_student3 = new Student("Nathan", "Cairns", "ncai762");
		_student4 = new Student("Buster", "Major", "mjar");
		
		_teacher1 = new Teacher("mr", "teacher", "mt");
		_teacher2 = new Teacher("man", "Teacer", "The guy");
	}
	
	@Test
	public void testSavingFile() {
		_student.saveUser();
	}
	
	@Test
	public void testSaveClassroom() {
		c.addStudent(_student);
		c.addStudent(_student1);
		c.addStudent(_student2);
		c.addStudent(_student3);
		c.addStudent(_student4);
		
		c.addTeacher(_teacher1);
		c.addTeacher(_teacher2);
		
		c.saveRoll();
	}

}
