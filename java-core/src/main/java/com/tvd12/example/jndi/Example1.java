package com.tvd12.example.jndi;

import java.util.Hashtable;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

public class Example1 {
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void main(String[] rgstring) {
        try {
            // Create the initial context. The environment
            // information specifies the JNDI provider to use
            // and the initial URL to use (in our case, a
            // directory in URL form -- file:///...).
            Hashtable hashtableEnvironment = new Hashtable();
            hashtableEnvironment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
            hashtableEnvironment.put(Context.PROVIDER_URL, rgstring[0]);
            Context context = new InitialContext(hashtableEnvironment);
            // If you provide no other command line arguments,
            // list all of the names in the specified context and
            // the objects they are bound to.
            if (rgstring.length == 1) {
                NamingEnumeration namingenumeration = context.listBindings("");
                while (namingenumeration.hasMore()) {
                    Binding binding = (Binding) namingenumeration.next();
                    System.out.println(binding.getName() + " " + binding.getObject());
                }
            }
            // Otherwise, list the names and bindings for the
            // specified arguments.
            else {
                for (int i = 1; i < rgstring.length; i++) {
                    Object object = context.lookup(rgstring[i]);
                    System.out.println(rgstring[i] + " " + object);
                }
            }
            context.close();
        } catch (NamingException namingexception) {
            namingexception.printStackTrace();
        }
    }
}
