package main.java.translator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is responsible for evaluating equations in the form of strings to
 * their integer equivalence. For example: the string "4+2=" evaluates to int : 6.
 * Inout strings must be in the format of an integer x, followed by one of the
 * following operators: "+, -, x, or ÷", then followed by another integer. Anything
 * after the second integer is not considered.
 * 
 * @author Buster Darragh-Major
 *
 */
public class TataiEquationTranslator implements Translator {

	private Translator _translator = new TataiTranslator();
	
	public String translate(String toTranslate) {
		
		List<String> nums = new ArrayList<String>();

		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(toTranslate);
		
		while (m.find()) {
			nums.add(m.group());
		}
		
		int ans = 0;
		if (toTranslate.contains("+")) {
			ans = Integer.parseInt(nums.get(0)) + Integer.parseInt(nums.get(1));
		} else if (toTranslate.contains("-")) {
			ans = Integer.parseInt(nums.get(0)) - Integer.parseInt(nums.get(1));
		} else if (toTranslate.contains("x")) {
			ans = Integer.parseInt(nums.get(0)) * Integer.parseInt(nums.get(1));
		} else if (toTranslate.contains("÷")) {
			ans = Integer.parseInt(nums.get(0)) / Integer.parseInt(nums.get(1));
		}
		
		return _translator.translate(Integer.toString(ans));
	}
}