package main.java.creations.labelgenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * This class deals with the responsibility of generating random strings of equations
 * in the form "x(+or-)y=", where x and y are integers between 1 and 9, and the equation
 * evaluates to an integer between 1 and 99. Calling the GenerateLabel() method will return
 * this String. Addition and subtraction occurs in parent class
 * 
 * @author Buster Darragh-Major
 * @author Nathan Cairns
 */
public class Level2EquationLabelGenerator extends Level1EquationLabelGenerator {

	/* Macros */
	public static final int MIN = 1;
	public static final int MAX = 99;
	public static final int[] NON_PRIMES = {4,6,8,9,10,12};
	private static final int RANDOM_OPERATOR_CONST = 4;
	
	/**
	 * H=Randomly chooses operator for equation to be
	 */
	@Override
	public void chooseOperator(int maximum, int minimum) {
		int rand = (int) (RANDOM_OPERATOR_CONST * Math.random());
		
		if (rand == 0) {
			generateAddition(MAX, MIN);
		}  else if (rand == 1) {
			generateSubratction(MAX, MIN);
		} else if (rand == 2) {
			generateMultiplication(maximum, minimum);
		} else if (rand == 3) {
			generateDivision(maximum, minimum);
		}
	}
	
	/**
	 * Randomly generate multiplication equation with answer between 1 and 99.
	 * operands themselves cannot be more than 12 in this implementation
	 * @param maximum
	 * @param minimum
	 */
	protected void generateMultiplication(int maximum , int minimum) {
		int operand1 = getRandomInteger(12, MIN);
		
		int operand2;
		
		if (operand1 >= 10 && operand1 < 12) {
			operand2 = getRandomInteger(Level1LabelGenerator.MAX, MIN);
		} else if (operand1 == 12){
			operand2 = getRandomInteger(8, MIN);
		} else {
			operand2 = getRandomInteger(12, MIN);
		}
		
		_label = operand1 + MULTIPLICATION + operand2 + EQUALS;
	}
	
	/**
	 * Generate a random division equation with first operand being non prime <= 12. 
	 * Question only tests multiples of 12 at max.
	 * @param maximum
	 * @param minimum
	 */
	protected void generateDivision(int maximum , int minimum) {
		int rand = (int) (NON_PRIMES.length * Math.random());
		
		int operand1 = NON_PRIMES[rand];
		
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
}
