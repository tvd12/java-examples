package com.tvd12.javaexample.jndi.my;

import javax.naming.CompositeName;
import javax.naming.Name;
import javax.naming.NamingException;

public class TestNameParser2 {

	public static void main(String[] args) throws NamingException {
		MyNameParser2 parser = new MyNameParser2();
		Name name = parser.parse("a/b/c=uid=dungtv,age=25");
		
		System.out.println("size = " + name.size() + "\n");
		System.out.println("uri = " + name.toString());
		System.out.println();
		
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
		
		System.out.println("\n====== composite name =======");
		Name name2 = new CompositeName(name.get(2));
		System.out.println(name2.get(0));
		System.out.println(name2.getSuffix(1));
		System.out.println(name2.getPrefix(1));
		
	}
	
}
