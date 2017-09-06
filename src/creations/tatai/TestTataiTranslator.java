package creations.tatai;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test class for TataiTranslator
 * 
 * @author Buster Major
 */
public class TestTataiTranslator {
	
	@Test
	public void testOne() {
		TataiTranslator t = new TataiTranslator(1);
		assertEquals("tahi", t.asMaori());
	}
	
	@Test
	public void testSix() {
		TataiTranslator t = new TataiTranslator(6);
		assertEquals("ono", t.asMaori());
	}
	
	@Test
	public void testTen() {
		TataiTranslator t = new TataiTranslator(10);
		assertEquals("tekau", t.asMaori());
	}
	
	@Test
	public void testEleven() {
		TataiTranslator t = new TataiTranslator(11);
		assertEquals("tekau mā tahi", t.asMaori());
	}
	
	@Test
	public void testSeventeen() {
		TataiTranslator t = new TataiTranslator(17);
		assertEquals("tekau mā whitu", t.asMaori());
	}
	
	@Test
	public void testTwenty() {
		TataiTranslator t = new TataiTranslator(20);
		assertEquals("rua tekau", t.asMaori());
	}
	
	@Test
	public void testTwentyTwo() {
		TataiTranslator t = new TataiTranslator(22);
		assertEquals("rua tekau mā rua", t.asMaori());
	}
	
	@Test
	public void testThirty() {
		TataiTranslator t = new TataiTranslator(30);
		assertEquals("toru tekau", t.asMaori());
	}
	
	@Test
	public void testForty() {
		TataiTranslator t = new TataiTranslator(40);
		assertEquals("whā tekau", t.asMaori());
	}
	
	@Test
	public void testFifty() {
		TataiTranslator t = new TataiTranslator(50);
		assertEquals("rima tekau", t.asMaori());
	}
	
	@Test
	public void testSixty() {
		TataiTranslator t = new TataiTranslator(60);
		assertEquals("ono tekau", t.asMaori());
	}
	
	@Test
	public void testSeventy() {
		TataiTranslator t = new TataiTranslator(70);
		assertEquals("whitu tekau", t.asMaori());
	}
	
	@Test
	public void testNinety() {
		TataiTranslator t = new TataiTranslator(90);
		assertEquals("iwa tekau", t.asMaori());
	}
	
	@Test
	public void testNinetyNine() {
		TataiTranslator t = new TataiTranslator(99);
		assertEquals("iwa tekau mā iwa", t.asMaori());
	}
	
	@Test
	public void testSixtyFive() {
		TataiTranslator t = new TataiTranslator(65);
		assertEquals("ono tekau mā rima", t.asMaori());
	}
	
	@Test
	public void testFiftyFour() {
		TataiTranslator t = new TataiTranslator(54);
		assertEquals("rima tekau mā whā", t.asMaori());
	}
}
