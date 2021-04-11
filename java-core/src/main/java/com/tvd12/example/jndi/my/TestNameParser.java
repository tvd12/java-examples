package com.tvd12.example.jndi.my;

import javax.naming.Name;
import javax.naming.NamingException;

public class TestNameParser {

	public static void main(String[] args) throws NamingException {
		MyNameParser parser = new MyNameParser();
		Name name = parser.parse("a.b.c");
		
		System.out.println("size = " + name.size() + "\n");
		
		System.out.println("====== get =======");
		System.out.println(name.get(0));
		System.out.println(name.get(1));
		System.out.println(name.get(2));
		
		System.out.println("\n====== getSuffix =======");
		System.out.println(name.getSuffix(0));
		System.out.println(name.getSuffix(1));
		System.out.println(name.getSuffix(2));
		System.out.println(name.getSuffix(3));
		
		System.out.println("\n====== getPrefix =======");
		System.out.println(name.getPrefix(0));
		System.out.println(name.getPrefix(1));
		System.out.println(name.getPrefix(2));
		System.out.println(name.getPrefix(3));
	}
	
}
