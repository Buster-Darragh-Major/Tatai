package main.java.creations.labelgenerator;

import main.java.questionlist.TextQuestionListHandler;

/**
 * This class deals with the responsibility of generating random strings of equations
 * in the form "x(+or-orxor÷)y=", where x and y are integers between 1 and 99, and the equation
 * evaluates to an integer between 1 and 99. Calling the GenerateLabel() method will return
 * this String .
 * @author Buster Darragh-Major
 * @author Nathan Cairns
 */
public class CustomEquationLabelGenerator implements LabelGenerator {
	
	/* Fields */
	private TextQuestionListHandler _handler;
	private int _questionNo = 1;
	
	public CustomEquationLabelGenerator(TextQuestionListHandler handler) {
		_handler = handler;
	}
	
	/**
	 * Gets the current equation string read off the corresponding line of the text file.
	 * When this method is called the current question number in the handler is incremented,
	 * so no intermediate method needs to be called to increment the question number. I.e., 
	 * generateLabel() will return the next question string every time when called successively.
	 */
	@Override
	public String generateLabel() {
		String equation = _handler.getLine(_questionNo);
		_questionNo++;
		
		return equation;
	}
}
