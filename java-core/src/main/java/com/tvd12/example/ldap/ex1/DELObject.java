package com.tvd12.example.ldap.ex1;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;


/**
 * This example shows how to remove an entry from the LDAP server.
 * Removes the entry with DN uid=styagi, ou=People, o=myserver.com
 * Also removes the stored Vector with DN cn=vectorid-9,ou=JavaObjects,o=myserver.com
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class DELObject {
    public static void main(String args[]) {
        Hashtable env = new Hashtable(11);
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:389");
        try {
            // Create the initial context
            Context ctx = new InitialContext(env);
            ctx.destroySubcontext("uid=styagi, ou=People, o=myserver.com");
            ctx.destroySubcontext("cn=vectorid-9,ou=JavaObjects,o=myserver.com");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
