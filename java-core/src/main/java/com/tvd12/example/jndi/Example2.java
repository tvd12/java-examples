package com.tvd12.example.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;

public class Example2 {

    public static void main(String[] args) throws NamingException {
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, InitCtxFactory.class.getName());
        Context context = new InitialContext();
        context.bind("my-object", new MyObject());
    }

    public static class InitCtxFactory implements InitialContextFactory {

        public Context getInitialContext(Hashtable<?, ?> environment) {
            try {
                return new InitialContext(environment);
            } catch (NamingException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

}
