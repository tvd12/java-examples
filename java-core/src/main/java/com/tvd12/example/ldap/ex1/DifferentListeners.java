package com.tvd12.example.ldap.ex1;

import javax.naming.*;
import javax.naming.event.*;
import java.util.*;
import javax.naming.directory.*;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class DifferentListeners {

public static void main(String args[]){

if(args.length==0){
		System.out.println("Usage :java DifferentSearches [EventContext |EventDirContext]");
		System.exit(0);
	}
DifferentListeners dl = new DifferentListeners();

if(args[0].equalsIgnoreCase("EventContext"))
	    dl.doEvtListener();
else if(args[0].equalsIgnoreCase("EventDirContext"))
	  dl.doEvtDirListener();
else
     System.out.println("Usage :java DifferentSearches [EventContext |EventDirContext]");

}


/** Method to show usage of an adding an NamespaceChangeListener and an EventContext
*/
public void doEvtListener(){

try{
	Context inictx =getContext();
	EventContext ctx=(EventContext)(inictx.lookup("o=myserver.com"));
	ctx.unbind("cn=eventtestrenamed,ou=JavaObjects"); // If this example is being rerun multiple times
	NamingListener objlsnr =new MyNamingListener();
	ctx.addNamingListener("ou=JavaObjects",EventContext.SUBTREE_SCOPE, objlsnr);
	ctx.bind("cn=eventtest,ou=JavaObjects",new Vector());
	ctx.rename("cn=eventtest,ou=JavaObjects","cn=eventtestrenamed,ou=JavaObjects");
	Thread.sleep(3000);
	ctx.close();
}catch(Exception e){
	System.out.println(e.toString());
}
}



/** Method to show usage of an adding a NamespaceChangeListener and an EventDirContext
  * Make sure that ADDSerailize.java is executed so that the search returns some resulst before
  * running this example
 */
public void doEvtDirListener(){
try{
	Context inictx =getDirContext();
	EventDirContext ctx = (EventDirContext)(inictx.lookup("o=myserver.com"));
	NamingListener naminglsnr= new MyNamingListener();

	SearchControls ctls = new SearchControls();
	ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
	ctx.addNamingListener("ou=JavaObjects","(cn=*)",ctls, naminglsnr);
	// Test the listener
	ctx.unbind("cn=vectorid-9,ou=JavaObjects");
	Thread.sleep(3000);
	ctx.close();
	}catch(Exception e){
		System.out.println(e.toString());
	}
}



/** Generic method to obtain a reference to a DirContext
*/
public DirContext getDirContext() throws Exception{
	   Hashtable env = new Hashtable(11);
	   env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
	   env.put(Context.PROVIDER_URL, "ldap://localhost:389");
	   // Create the initial context
	   DirContext ctx = new InitialDirContext(env);
	   return ctx;

    }

/** Generic method to obtain a reference to a Context object.
*/
public Context getContext() throws Exception{
		   Hashtable env = new Hashtable(11);
		   env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		   env.put(Context.PROVIDER_URL, "ldap://localhost:389");
		   // Create the initial context
		   Context ctx = new InitialContext(env);
		   return ctx;

    }

}