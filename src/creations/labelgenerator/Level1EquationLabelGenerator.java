package creations.labelgenerator;

/**
 * This class deals with the responsibility of generating random strings of equations
 * in the form "x(+or-)y=", where x and y are integers between 1 and 9, and the equation
 * evaluates to an integer between 1 and 9. Calling the GenerateLabel() method will return
 * this String.
 * @author Buster Darragh-Major
 * @author Nathan Cairns
 */
public class Level1EquationLabelGenerator extends Level1LabelGenerator{

	/* Macros */
	public static final String ADDITION = "+";
	public static final String MULTIPLICATION = "x";
	public static final String DIVISION = "÷";
	public static final String SUBTRACTION = "-";
	public static final String EQUALS = "=";
	public static final int MIN = 1;
	public static final int MAX = 9;
	
	/* Fields */
	protected String _label;
	
	/**
	 * Generates an equation with either addition or subtraction as its operand type.
	 * Minimum and maximum answers of said equations are set to 1 and 9 respectively.
	 * Every time this method is called a random string is generated.
	 */
	@Override
	public String generateLabel() {
		chooseOperator(MAX, MIN);
		
		return _label;
	}
	
	/**
	 * Randomly generates an operator string, either "+" or "-". It calls
	 * a further method that either generates a valid addition or subtraction
	 * equation, depending on the operator generated.
	 * @param maximum : int
	 * @param minimum : int
	 */
	protected void chooseOperator(int maximum, int minimum) {
		int rand = (int) (2 * Math.random());
		
		if (rand == 0) {
			generateAddition(maximum, minimum);
		}  else if (rand == 1) {
			generateSubratction(maximum, minimum);
		}
	}
	
	/**
	 * Generates a string based off an addition equation between two ints, the
	 * answer must evaluate to an integer between two parameters:
	 * @param maximum : int
	 * @param minimum : int
	 */
	protected void generateAddition(int maximum , int minimum) {
		int operand1 = getRandomInteger(maximum - minimum, minimum);
		
		int operand2Max = maximum - operand1;
		
		int operand2 = getRandomInteger(operand2Max, minimum);
		
		_label = operand1 + ADDITION + operand2 + EQUALS;
	}
	
	/**
	 * Generates a string based off an subtraction equation between two ints, the
	 * answer must evaluate to an integer between two parameters:
	 * @param maximum : int
	 * @param minimum : int
	 */
	protected void generateSubratction(int maximum , int minimum) {
		int operand1 = getRandomInteger(maximum, 2 * minimum);
		
		int operand2Max = operand1 - minimum;
		
		int operand2 = getRandomInteger(operand2Max, minimum);
		
		_label = operand1 + SUBTRACTION + operand2 + EQUALS;
	}
}
