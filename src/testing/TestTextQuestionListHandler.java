package testing;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import questionlist.TextQuestionListHandler;

public class TestTextQuestionListHandler {
	
	TextQuestionListHandler _handler;
	
	@Before
	public void setup() {
		_handler = new TextQuestionListHandler("Test");
		_handler.makeList();
		_handler.clear();
	}
	
	@After
	public void after() {
		_handler.delete();
	}
	
	@Test
	public void testReadLine() {
		_handler.writeToFile("cats");
		_handler.writeToFile("dogs");
		_handler.writeToFile("parrots");
		_handler.writeToFile("budgies");
		_handler.writeToFile("carrots");
		
		assertEquals(_handler.getLine(1), "cats");
		assertEquals(_handler.getLine(4), "budgies");
		assertEquals(_handler.getLine(100), null);
		assertEquals(_handler.getLine(0), null);	
	}
	
	@Test
	public void testDeleteByString() {
		_handler.writeToFile("dont delete");
		_handler.writeToFile("dont delete");
		_handler.writeToFile("delete");
		_handler.writeToFile("cats");
		_handler.writeToFile("delete");
		
		_handler.delete("delete");
		
		assertEquals(_handler.getLine(1), "dont delete");
		assertEquals(_handler.getLine(2), "dont delete");
		assertEquals(_handler.getLine(3), "cats");
		assertEquals(_handler.getLine(4), "delete");
	}
	
	
	@Test
	public void testDeleteByLineNo() {
		_handler.writeToFile("dont delete");
		_handler.writeToFile("dont delete");
		_handler.writeToFile("Should be deleted");
		_handler.writeToFile("dont delete");
		_handler.writeToFile("dont delete");
		
		_handler.delete(3);
		
		assertEquals(_handler.getLine(1), "dont delete");
		assertEquals(_handler.getLine(2), "dont delete");
		assertEquals(_handler.getLine(3), "dont delete");
		assertEquals(_handler.getLine(4), "dont delete");		
	}
	
	@Test
	public void testSize() {
		for (int i = 0; i < 100; i++) {
			_handler.writeToFile("yo");
		}
		
		assertEquals(_handler.size(), 100);		
	}
}
