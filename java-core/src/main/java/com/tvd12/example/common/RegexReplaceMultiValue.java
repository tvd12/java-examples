package com.tvd12.example.common;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Java Replacing multiple different substring in a string 
 * at once (or in the most efficient way)
 */
public class RegexReplaceMultiValue {

	public static void main(String[] args) {
		Map<String,String> tokens = new HashMap<String,String>();
		tokens.put("cat", "Garfield");
		tokens.put("beverage", "coffee");

		String template = "%cat% really needs some %beverage%.";

		// Create pattern of the format "%(cat|beverage)%"
		String patternString = "%(" + String.join("|", tokens.keySet()) + ")%";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(template);

		StringBuffer sb = new StringBuffer();
		while(matcher.find()) {
		    matcher.appendReplacement(sb, tokens.get(matcher.group(1)));
		}
		matcher.appendTail(sb);

		System.out.println(sb.toString());
	}
	
}
