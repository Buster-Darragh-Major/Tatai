package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import translator.TataiEquationTranslator;
import translator.Translator;

public class TestTataiEquationTranslator {

	Translator _translator;
	
	@Before
	public void setup() {
		_translator = new TataiEquationTranslator();
	}
	
	@Test
	public void testAddition() {
		String ans = _translator.translate("3+3=");
		assertEquals(ans, "ono");
		
		ans = _translator.translate("3+7=");
		assertEquals(ans, "tekau");
		
		ans = _translator.translate("20+36=");
		assertEquals(ans, "rima tekau mā ono");
	}
	
	@Test
	public void testSubtraction() {
		String ans = _translator.translate("3-2=");
		assertEquals(ans, "tahi");
		
		ans = _translator.translate("11-6=");
		assertEquals(ans, "rima");
		
		ans = _translator.translate("88-34=");
		assertEquals(ans, "rima tekau mā whā");
	}
	
	@Test
	public void testMutiplication() {
		String ans = _translator.translate("3x2=");
		assertEquals(ans, "ono");
		
		ans = _translator.translate("4x3=");
		assertEquals(ans, "tekau mā rua");
		
		ans = _translator.translate("5x10=");
		assertEquals(ans, "rima tekau");
	}
	
	@Test
	public void testDivision() {
		String ans = _translator.translate("4÷2=");
		assertEquals(ans, "rua");
		
		ans = _translator.translate("9÷3=");
		assertEquals(ans, "toru");
		
		ans = _translator.translate("12÷4=");
		assertEquals(ans, "toru");
	}
	
}
