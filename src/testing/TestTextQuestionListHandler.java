package testing;

import org.junit.Test;

import res.questionlist.TextQuestionListHandler;

public class TestTextQuestionListHandler {
	
	@Test
	public void testWrite() {
		TextQuestionListHandler handler = new TextQuestionListHandler("Test_01");
		handler.writeToFile("Heres a test!");
		handler.writeToFile("Heres another!!");
	}
	
	@Test
	public void testDeleteByString() {
		TextQuestionListHandler handler = new TextQuestionListHandler("TestDeleteStr");
		handler.writeToFile("dont delete");
		handler.writeToFile("dont delete");
		handler.writeToFile("delete");
		handler.writeToFile("cats");
		handler.writeToFile("delete");
		
		handler.delete("delete");
	}
	
	
	@Test
	public void testDeleteByLineNo() {
		TextQuestionListHandler handler = new TextQuestionListHandler("TestDeleteNum");
		handler.writeToFile("dont delete");
		handler.writeToFile("dont delete");
		handler.writeToFile("dont delete");
		handler.writeToFile("dont delete");
		handler.writeToFile("Should be deleted");
		handler.writeToFile("dont delete");
		handler.writeToFile("dont delete");
		handler.writeToFile("dont delete");
		handler.writeToFile("dont delete");
		
		handler.delete(5);
	}
}
