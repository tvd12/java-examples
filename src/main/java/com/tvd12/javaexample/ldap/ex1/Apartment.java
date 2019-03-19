package com.tvd12.javaexample.ldap.ex1;

import javax.naming.*;


/**
  * This class captures the abstraction of an Apartment object.
*/
public class Apartment implements Referenceable{
	private String size;
	public String location;

/**
  * Constructor to create an apartment with a size and location
  */
public Apartment(String size,String location){
	 this.size=size;
	 this.location=location;
}

/**
  * This method returns a Reference that captures all information necessary in a RefAddr or
  * subclasses of the RefAddr class or StringRefAddr
  */
public Reference getReference() throws NamingException{
	 String classname = Apartment.class.getName();
	 StringRefAddr classref =
				 new StringRefAddr("Apartment details", size+ ":" +location);
	 String classfactoryname=ApartmentFactory.class.getName();
	 Reference ref = new Reference(classname,classref,classfactoryname,null);
	 return ref;
}

/**
* toString method to print out intelligent information if needed about the Apartment
*/
public String toString(){
   return ("This apartment is "+size+ " and is located at " +location);
}
}
