package com.tvd12.example.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MyContextFactory implements javax.naming.spi.InitialContextFactory {

    public static void main(String[] args) throws NamingException {
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, MyContextFactory.class.getName());
        Context context = new InitialContext();
        System.out.println(context.lookup(""));
    }

    @Override
    public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
        return new MyContext();
    }

}
