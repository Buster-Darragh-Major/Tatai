package testing;

import org.junit.Before;
import org.junit.Test;

import creations.labelgenerator.Level1EquationLabelGenerator;
import creations.labelgenerator.Level2EquationLabelGenerator;

public class TestEquationLabelGeneratior {

	Level1EquationLabelGenerator _labelGenerator1;
	Level2EquationLabelGenerator _labelGenerator2;
	
	@Before
	public void setup() {
		_labelGenerator1 = new Level1EquationLabelGenerator();
		_labelGenerator2 = new Level2EquationLabelGenerator();
	}
	
	@Test
	public void testadd() {
		for (int i = 0; i < 10; i++) {
			System.out.println(_labelGenerator1.generateLabel());
		}
		System.out.println("---------");
		for (int i = 0; i < 10; i++) {
			System.out.println(_labelGenerator2.generateLabel());
		}
	}
	
}
