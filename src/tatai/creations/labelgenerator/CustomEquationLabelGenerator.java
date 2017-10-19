package tatai.creations.labelgenerator;

import questionlist.TextQuestionListHandler;

public class CustomEquationLabelGenerator implements LabelGenerator {
	
	/* Fields */
	private TextQuestionListHandler _handler;
	private int _questionNo = 1;
	
	public CustomEquationLabelGenerator(TextQuestionListHandler handler) {
		_handler = handler;
	}
	
	@Override
	public String generateLabel() {
		String equation = _handler.getLine(_questionNo);
		_questionNo++;
		
		return equation;
	}
}
