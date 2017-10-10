package tatai.creations.labelgenerator;

public class Level2EquationLabelGenerator extends Level1EquationLabelGenerator {

	/* Macros */
	public static final int MIN = 1;
	public static final int MAX = 99;
	
	@Override
	public String generateLabel() {
		chooseOperator(MAX, MIN);
		
		return _label;
	}
	
}
