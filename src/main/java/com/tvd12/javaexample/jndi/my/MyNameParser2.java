package com.tvd12.javaexample.jndi.my;

import java.util.Properties;

import javax.naming.CompoundName;
import javax.naming.Name;
import javax.naming.NameParser;
import javax.naming.NamingException;

public class MyNameParser2 implements NameParser {

	private static final Properties SYNTAX = new Properties();
	
	static {
		SYNTAX.put("jndi.syntax.direction", "left_to_right");
		SYNTAX.put("jndi.syntax.separator", "/");
		SYNTAX.put("jndi.syntax.separator.ava", ",");
		SYNTAX.put("jndi.syntax.separator.typeval", "=");
		SYNTAX.put("jndi.syntax.ignorecase", "false");
		SYNTAX.put("jndi.syntax.escape", "\n\n");
		SYNTAX.put("jndi.syntax.beginquote", "'");
		SYNTAX.put("jndi.syntax.endquote", "'");
	}
	
	@Override
	public Name parse(String name) throws NamingException {
		return new CompoundName(name, SYNTAX);
	}
}
