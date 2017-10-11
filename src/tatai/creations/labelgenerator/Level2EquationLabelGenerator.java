package tatai.creations.labelgenerator;

import java.util.ArrayList;
import java.util.List;

public class Level2EquationLabelGenerator extends Level1EquationLabelGenerator {

	/* Macros */
	public static final int MIN = 1;
	public static final int MAX = 99;
	public static final int[] NON_PRIMES = {2,4,6,8,9,10,12};
	
	@Override
	public void chooseOperator(int maximum, int minimum) {
		int rand = (int) (4 * Math.random());
		
		if (rand == 0) {
			generateAddition(maximum, minimum);
		}  else if (rand == 1) {
			generateSubratction(maximum, minimum);
		} else if (rand == 2) {
			generateMultiplication(maximum, minimum);
		} else if (rand == 3) {
			generateDivision(maximum, minimum);
		}
	}
	
	protected void generateMultiplication(int maximum , int minimum) {
		
		_label = "stub";
	}
	
	
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
