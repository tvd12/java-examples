package com.tvd12.example.ldap.ex1;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 * Example shows how to store a an object reference in the directory.
 */

@SuppressWarnings({"unchecked", "rawtypes"})
public class ADDReference {

    public static void main(String[] args) {

        // Set up environment for creating initial context
        Hashtable env = new Hashtable(11);
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:10389/o=dungtv");

        try {
            // Create the initial context
            DirContext ctx = new InitialDirContext(env);
            try {
                ctx.bind("cn=styagi", new Apartment("studio", "Mill Complex"));
            } catch (Exception e) {
            }
            try {
                BasicAttributes battr = new BasicAttributes("apartment", "mojoe");
                ctx.bind("cn=mojoe", new Apartment("2 room", "Farm House Apartments"), battr);
            } catch (Exception e) {
//				e.printStackTrace();
            }
            // ctx.bind("apartment=janedoe,ou=JavaObjects,o=myserver.com",new
            // Apartment("1 room","Pond Side"));
            // ctx.bind("apartment=rogerp,ou=JavaObjects,o=myserver.com", new
            // Apartment("3 room","Mill Complex"));
            // ctx.bind("apartment=jamesm,ou=JavaObjects,o=myserver.com", new
            // Apartment("studio","Fox Hill Apartments"));
            // ctx.bind("apartment=paulh,ou=JavaObjects,o=myserver.com", new
            // Apartment("duplex","Woodbridge"));
            // ctx.bind("apartment=vkevink,ou=JavaObjects,o=myserver.com", new
            // Apartment("1 room","Woodgate Apartments"));

            // Apartment apt =
            // (Apartment)ctx.lookup("apartment=styagi,ou=JavaObjects,o=myserver.com");
            Apartment apt = (Apartment) ctx.lookup("cn=styagi");
            System.out.println(apt);

            // Close the context when we're done
            ctx.close();
        } catch (

            NamingException e) {
            System.out.println("Operation failed: " + e);
        }
    }
}