package com.tvd12.javaexample.javassist;

public class MyStringBuilder {

	public String buildString(int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += (char)(i%26 + 'a');
        }
        return result;
    }
	
}
