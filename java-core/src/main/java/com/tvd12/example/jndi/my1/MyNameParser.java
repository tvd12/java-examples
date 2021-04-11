package com.tvd12.example.jndi.my1;

import java.util.Properties;

import javax.naming.CompoundName;
import javax.naming.Name;
import javax.naming.NameParser;
import javax.naming.NamingException;

public class MyNameParser implements NameParser {

	private static final Properties SYNTAX = new Properties();
	
	static {
		SYNTAX.put("jndi.syntax.direction", "left_to_right");
		SYNTAX.put("jndi.syntax.separator", "/");
		SYNTAX.put("jndi.syntax.ignorecase", "false");
		SYNTAX.put("jndi.syntax.escape", "\\");
		SYNTAX.put("jndi.syntax.beginquote", "'");
	}
	
	@Override
	public Name parse(String name) throws NamingException {
		return new CompoundName(name, SYNTAX);
	}
}
