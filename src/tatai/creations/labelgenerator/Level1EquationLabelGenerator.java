package tatai.creations.labelgenerator;

public class Level1EquationLabelGenerator implements LabelGenerator {

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
	
	@Override
	public String generateLabel() {
		chooseOperator(MAX, MIN);
		
		return _label;
	}
	
	protected void chooseOperator(int maximum, int minimum) {
		int rand = (int) (4 * Math.random());
		rand = 3;
		
		if (rand == 0) {
			generateAddition(maximum, minimum);
		} else if (rand == 1) {
			generateMultiplication(maximum, minimum);
		} else if (rand == 2) {
			generateDivision(maximum, minimum);
		} else if (rand == 3) {
			generateSubratction(maximum, minimum);
		}
	}

	protected void generateAddition(int maximum , int minimum) {
		int operand1 = getRandomInteger(maximum - minimum, minimum);
		
		int operand2Max = maximum - operand1;
		
		int operand2 = getRandomInteger(operand2Max, minimum);
		
		_label = operand1 + ADDITION + operand2 + EQUALS;
	}
	
	
	
	protected void generateMultiplication(int maximum , int minimum) {
		
	}
	
	
	
	protected void generateDivision(int maximum , int minimum) {
		
	}
	
	
	
	protected void generateSubratction(int maximum , int minimum) {
		int operand1 = getRandomInteger(maximum, 2 * minimum);
		
		int operand2Max = operand1 - minimum;
		
		int operand2 = getRandomInteger(operand2Max, minimum);
		
		_label = operand1 + SUBTRACTION + operand2 + EQUALS;
	}
	
	
	/**
	 * returns a random number between input parameters maximum and minimum.
	 * @param maximum
	 * @param minimum
	 * @return int
	 */
	public int getRandomInteger(int maximum, int minimum) {
		return ((int) (Math.random()*(maximum))) + minimum;
	}
}
