package com.tvd12.example;

public class AlphabetSubstring {

	boolean alphabetSubstring(String s) {
		return s.chars().sum() - s.charAt(0) == s.length();
	}

}
