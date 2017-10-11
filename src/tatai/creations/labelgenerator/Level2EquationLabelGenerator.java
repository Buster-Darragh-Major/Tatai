package tatai.creations.labelgenerator;

import java.util.ArrayList;
import java.util.List;

public class Level2EquationLabelGenerator extends Level1EquationLabelGenerator {

	/* Macros */
	public static final int MIN = 1;
	public static final int MAX = 99;
	
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
	
	private static boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i * i <= num; i += 2)
            if (num % i == 0) return false;
        return true;
	}
	
}
