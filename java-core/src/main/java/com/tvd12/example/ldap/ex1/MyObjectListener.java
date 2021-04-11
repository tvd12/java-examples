package com.tvd12.example.ldap.ex1;

import javax.naming.event.NamingEvent;
import javax.naming.event.NamingExceptionEvent;
import javax.naming.event.ObjectChangeListener;

/**
 * This example shows how to implement a Object Listener to trap Naming events
 * in the LDAP server.
 */
public class MyObjectListener implements ObjectChangeListener {

	public void objectChanged(NamingEvent event) {
		System.out.println("Object Listener detected that an object binding changed  from " 
				+ event.getOldBinding()
				+ " to " 
				+ event.getNewBinding());
		System.out.println("");
	}

	public void namingExceptionThrown(NamingExceptionEvent event) {
		System.out.println("Object listener got an exception");
		event.getException().printStackTrace();
	}
}
