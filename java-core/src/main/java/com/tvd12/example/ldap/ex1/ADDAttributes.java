package com.tvd12.example.ldap.ex1;

import java.util.Hashtable;
import javax.naming.*;
import javax.naming.directory.*;


/**
 *This example shows how to add attributes in the ldap server by adding an object.
 *Creates  7 instances of the user object and stores them in the database
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ADDAttributes{
   
	public static void main(String args[]){
      try {
         //Hashtable for environmental information
    	Hashtable env = new Hashtable(11);
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:10389/o=dungtv");

         //Get a reference to a directory context
         DirContext ctx = new InitialDirContext(env);


		User p1 = new User("styagi", "Sameer", "Tyagi", "ou=People","styagi@myserver.com");
		User p2 = new User("mojoe", "Moe", "Joe", "ou=People","mojoe@myserver.com");
		User p3 = new User("janedoe", "Jane", "Doe", "ou=People","janedoe@myserver.com");
		User p4 = new User("rogerp", "Roger", "Potter", "ou=People","rogerp@myserver.com");
		User p5 = new User("jamesm", "James", "Manson", "ou=People","jamesm@myserver.com");
		User p6 = new User("paulh", "Paul", "Harding", "ou=People","paulh@myserver.com");
		User p7 = new User("kevink", "Kevin", "Klunk", "ou=People","kevink@myserver.com");

        ctx.bind("uid=styagi", p1);
        ctx.bind("uid=mojoe", p2);
        ctx.bind("uid=janedoe", p3);
        ctx.bind("uid=rogerp", p4);
        ctx.bind("uid=jamesm", p5);
        ctx.bind("uid=paulh", p6);
        ctx.bind("uid=kevink", p7);
        ctx.close();
 	    }
      catch(Exception e)
      {
         e.printStackTrace();
         System.exit(1);
      }

   }
}
