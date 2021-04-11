package com.tvd12.example.ldap.ex1;

import javax.naming.event.NamespaceChangeListener;
import javax.naming.event.NamingEvent;
import javax.naming.event.NamingExceptionEvent;


/**
 *This example shows how to implement a Naming Listener to trap Naming events in the LDAP server.
 */
public class MyNamingListener implements NamespaceChangeListener{

  public void objectAdded(NamingEvent event) {
	  System.out.println("Naming Listener detected that an object was added:= " + event.getNewBinding());
	  System.out.println("");
  }
  public void objectRemoved(NamingEvent event) {
	  System.out.println("Naming Listener detected that an object was removed:= " + event.getOldBinding());
	  System.out.println("");
  }

  public void objectRenamed(NamingEvent event) {
	  System.out.println("Naming Listener detected that an object was := " + event.getNewBinding() + " from " + event.getOldBinding());
	  System.out.println("");
  }

  public void namingExceptionThrown(NamingExceptionEvent event) {
	  System.out.println("Naming Listener got an exception");
	  event.getException().printStackTrace();

  }

}
