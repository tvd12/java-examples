package com.tvd12.javaexample.jndi.my1;

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
			System.out.println(ctx.lookup("a/b"));
			System.out.println(a.lookup("b/c"));
			
			MyObject myObject1 = new MyObject();
			ctx.bind("m", myObject1);
			System.out.println(ctx.lookup("m"));
			MyObject myObject2 = new MyObject();
			myObject2.setName("haha");
			c.bind("m1", myObject2);
			System.out.println(ctx.lookup("a/b/c/m1"));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
}
