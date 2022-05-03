package com.tvd12.example.jndi.my;

import java.util.Properties;

import javax.naming.CompoundName;
import javax.naming.Name;
import javax.naming.NameParser;
import javax.naming.NamingException;

public class MyNameParser implements NameParser {

    private static final Properties SYNTAX = new Properties();

    static {
        SYNTAX.put("jndi.syntax.direction", "right_to_left");
        SYNTAX.put("jndi.syntax.separator", ".");
        SYNTAX.put("jndi.syntax.ignorecase", "false");
        SYNTAX.put("jndi.syntax.escape", "\n\n");
        SYNTAX.put("jndi.syntax.beginquote", "'");
    }

    @Override
    public Name parse(String name) throws NamingException {
        return new CompoundName(name, SYNTAX);
    }
}
