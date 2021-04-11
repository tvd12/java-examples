package com.tvd12.example.ldap.ex1;

import javax.naming.*;
import java.util.*;

/**
  * This example shows how to store a Serializable object to a directory.
  * can be removed with context.unbind() or a destroysubcontext() method
  */

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ADDSerialize {

    public static void main(String[] args) {

        // Set up environment for creating initial context
        Hashtable env = new Hashtable(11);
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:10389/o=dungtv");

        try {
            // Create the initial context
            Context ctx = new InitialContext(env);

			for(int i=0;i<10;i++){
				String id="vectorid-"+i;
			    Vector vec = new Vector();
			    String cn="cn="+id;//+",ou=JavaObjects,o=myserver.com";
			    ctx.bind(cn, vec);
			}

            // Close the context when we're done
            ctx.close();
        } catch (NamingException e) {
            System.out.println("Operation failed: " + e);
        }
    }
}