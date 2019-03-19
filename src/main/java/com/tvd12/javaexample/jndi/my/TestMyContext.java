package com.tvd12.javaexample.jndi.my;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;

import com.tvd12.javaexample.jndi.MyObject;

public class TestMyContext {

	static MyContext testRoot;
	static {
		try {
			testRoot = new MyContext(null);

			Context a = testRoot.createSubcontext("a");
			Context b = a.createSubcontext("b");
			b.createSubcontext("c");

			testRoot.createSubcontext("x");
			testRoot.createSubcontext("y");
		} catch (NamingException e) {
		}
	}

	@SuppressWarnings("rawtypes")
	public static Context getStaticNamespace(Hashtable env) {
		return testRoot;
	}

	public static void main(String[] args) {
		try {
			Context ctx = new MyContext(null);

			Context a = ctx.createSubcontext("a");
			Context b = a.createSubcontext("b");
			Context c = b.createSubcontext("c");

			System.out.println(c.getNameInNamespace());

			System.out.println(ctx.lookup(""));
			System.out.println(ctx.lookup("a"));
			System.out.println(ctx.lookup("b.a"));
			System.out.println(a.lookup("c.b"));
			
			MyObject myObject = new MyObject();
			ctx.bind("m", myObject);
			System.out.println(ctx.lookup("m"));
			c.bind("m1", myObject);
			System.out.println(ctx.lookup("m1.c.b.a"));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
}
