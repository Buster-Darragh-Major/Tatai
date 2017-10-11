package tatai.creations.labelgenerator;

import java.util.ArrayList;
import java.util.List;

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
		
		_label = "stub";
	}
	
	
	
	protected void generateDivision(int maximum , int minimum) {
		int operand1 = getRandomInteger(maximum, 2 * minimum);
		while (isPrime(operand1)) {
			operand1 = getRandomInteger(maximum, 2 * minimum);
		}
		
		// create list of divisible numbers by i
		List<Integer> operand2Range = new ArrayList<Integer>();
		for (int i = maximum; i > 1; i--) {
			if ((operand1 % i == 0) && (i != operand1)) {
				operand2Range.add(i);
			}
		}
		
		int arrayLength = operand2Range.size();
		int operand2 = operand2Range.get(getRandomInteger(arrayLength, 0));
		
		_label = operand1 + DIVISION + operand2 + EQUALS;
	}
	
	
	
	protected void generateSubratction(int maximum , int minimum) {
		int operand1 = getRandomInteger(maximum, 2 * minimum);
		
		int operand2Max = operand1 - minimum;
		
		int operand2 = getRandomInteger(operand2Max, minimum);
		
		_label = operand1 + SUBTRACTION + operand2 + EQUALS;
	}
	
	
	
	private static boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i * i <= num; i += 2)
            if (num % i == 0) return false;
        return true;
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
