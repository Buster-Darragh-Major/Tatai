package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import translator.TataiTranslator;
import translator.Translator;
import translator.TranslatorException;

/**
 * Test class for TataiTranslator
 * 
 * @author Buster Major
 */
public class TestTataiTranslator {
	
	Translator _translator;
	
	@Before
	public void setup() {
		_translator = new TataiTranslator();
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testInvalidNo() {
		try {
			String ans = _translator.translate("0");
			fail();
		} catch (TranslatorException e) {
		}
		
		try {
			@SuppressWarnings("unused")
			String ans = _translator.translate("100");
			fail();
		} catch (TranslatorException e) {
		}
		
		try {
			@SuppressWarnings("unused")
			String ans = _translator.translate("-40");
			fail();
		} catch (TranslatorException e) {
		}
		
		try {
			@SuppressWarnings("unused")
			String ans = _translator.translate("12684");
			fail();
		} catch (TranslatorException e) {
		}
		
		try {
			String ans = _translator.translate("hi");
			fail();
		} catch (TranslatorException e) {
		}
	}
	
	@Test
	public void testOne() {
		String ans = _translator.translate("1");
		assertEquals("tahi", ans);
	}
	
	@Test
	public void testSix() {
		String ans = _translator.translate("6");
		assertEquals("ono", ans);
	}
	
	@Test
	public void testTen() {
		String ans = _translator.translate("10");
		assertEquals("tekau", ans);
	}
	
	@Test
	public void testEleven() {
		String ans = _translator.translate("11");
		assertEquals("tekau mā tahi", ans);
	}
	
	@Test
	public void testSeventeen() {
		String ans = _translator.translate("17");
		assertEquals("tekau mā whitu", ans);
	}
	
	@Test
	public void testTwenty() {
		String ans = _translator.translate("20");
		assertEquals("rua tekau", ans);
	}
	
	@Test
	public void testTwentyTwo() {
		String ans = _translator.translate("22");
		assertEquals("rua tekau mā rua", ans);
	}
	
	@Test
	public void testThirty() {
		String ans = _translator.translate("30");
		assertEquals("toru tekau", ans);
	}
	
	@Test
	public void testForty() {
		String ans = _translator.translate("40");
		assertEquals("whā tekau", ans);
	}
	
	@Test
	public void testFifty() {
		String ans = _translator.translate("50");
		assertEquals("rima tekau", ans);
	}
	
	@Test
	public void testSixty() {
		String ans = _translator.translate("60");
		assertEquals("ono tekau", ans);
	}
	
	@Test
	public void testSeventy() {
		String ans = _translator.translate("70");
		assertEquals("whitu tekau", ans);
	}
	
	@Test
	public void testNinety() {
		String ans = _translator.translate("90");
		assertEquals("iwa tekau", ans);
	}
	
	@Test
	public void testNinetyNine() {
		String ans = _translator.translate("99");
		assertEquals("iwa tekau mā iwa", ans);
	}
	
	@Test
	public void testSixtyFive() {
		String ans = _translator.translate("65");
		assertEquals("ono tekau mā rima", ans);
	}
	
	@Test
	public void testFiftyFour() {
		String ans = _translator.translate("54");
		assertEquals("rima tekau mā whā", ans);
	}
}
