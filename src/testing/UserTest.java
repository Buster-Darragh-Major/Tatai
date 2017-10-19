package testing;

import org.junit.Before;
import org.junit.Test;

import users.user.Student;

public class UserTest {
	Student _student;
	
	@Before
	public void setup() {
		_student = new Student("Barry", "Bonds", "bboy");
	}
	
	@Test
	public void testSavingFile() {
		_student.saveUser();
	}

}
