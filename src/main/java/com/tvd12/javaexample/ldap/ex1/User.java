package com.tvd12.javaexample.ldap.ex1;

import java.util.*;
import javax.naming.*;
import javax.naming.directory.*;

/**
 * This class shows how to capture attributes in an object. Used to store
 * attributes on the server if the object is not serializeable
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class User implements DirContext {
	String type;
	Attributes myAttrs;

	public User(String uid, String givenname, String sn, String ou, String mail) {
		type = uid;

		myAttrs = new BasicAttributes(true);

		Attribute oc = new BasicAttribute("objectclass");
		oc.add("inetOrgPerson");
		oc.add("organizationalPerson");
		oc.add("person");
		oc.add("top");

		Attribute ouSet = new BasicAttribute("ou");
		ouSet.add("People");
		ouSet.add(ou);

		String cn = givenname + " " + sn;

		myAttrs.put(oc);
		myAttrs.put(ouSet);
		myAttrs.put("uid", uid);
		myAttrs.put("cn", cn);
		myAttrs.put("sn", sn);
		myAttrs.put("givenname", givenname);
		myAttrs.put("mail", mail);
	}

	public Attributes getAttributes(String name) throws NamingException {
		if (!name.equals("")) {
			throw new NameNotFoundException();
		}
		return myAttrs;
	}

	public Attributes getAttributes(Name name) throws NamingException {
		return getAttributes(name.toString());
	}

	public Attributes getAttributes(String name, String[] ids) throws NamingException {
		if (!name.equals("")) {
			throw new NameNotFoundException();
		}

		Attributes answer = new BasicAttributes(true);
		Attribute target;
		for (int i = 0; i < ids.length; i++) {
			target = myAttrs.get(ids[i]);
			if (target != null) {
				answer.put(target);
			}
		}
		return answer;
	}

	public Attributes getAttributes(Name name, String[] ids) throws NamingException {
		return getAttributes(name.toString(), ids);
	}

	public String toString() {
		return type;
	}

	// NOT used for this example but need to be defined.

	public Object lookup(Name name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public Object lookup(String name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public void bind(Name name, Object obj) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public void bind(String name, Object obj) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public void rebind(Name name, Object obj) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public void rebind(String name, Object obj) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public void unbind(Name name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public void unbind(String name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public void rename(Name oldName, Name newName) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public void rename(String oldName, String newName) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public NamingEnumeration list(Name name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public NamingEnumeration list(String name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public NamingEnumeration listBindings(Name name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public NamingEnumeration listBindings(String name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public void destroySubcontext(Name name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public void destroySubcontext(String name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public Context createSubcontext(Name name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public Context createSubcontext(String name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public Object lookupLink(Name name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public Object lookupLink(String name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public NameParser getNameParser(Name name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public NameParser getNameParser(String name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public String composeName(String name, String prefix) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public Name composeName(Name name, Name prefix) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public Object addToEnvironment(String propName, Object propVal) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public Object removeFromEnvironment(String propName) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public Hashtable getEnvironment() throws NamingException {
		throw new OperationNotSupportedException();
	}

	public void close() throws NamingException {
		throw new OperationNotSupportedException();
	}

	public void modifyAttributes(Name name, int mod_op, Attributes attrs) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public void modifyAttributes(String name, int mod_op, Attributes attrs) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public void modifyAttributes(Name name, ModificationItem[] mods) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public void modifyAttributes(String name, ModificationItem[] mods) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public void bind(Name name, Object obj, Attributes attrs) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public void bind(String name, Object obj, Attributes attrs) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public void rebind(Name name, Object obj, Attributes attrs) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public void rebind(String name, Object obj, Attributes attrs) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public DirContext createSubcontext(Name name, Attributes attrs) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public DirContext createSubcontext(String name, Attributes attrs) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public DirContext getSchema(Name name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public DirContext getSchema(String name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public DirContext getSchemaClassDefinition(Name name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public DirContext getSchemaClassDefinition(String name) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public NamingEnumeration search(Name name, Attributes matchingAttributes, String[] attributesToReturn)
			throws NamingException {
		throw new OperationNotSupportedException();
	}

	public NamingEnumeration search(String name, Attributes matchingAttributes, String[] attributesToReturn)
			throws NamingException {
		throw new OperationNotSupportedException();
	}

	public NamingEnumeration search(Name name, Attributes matchingAttributes) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public NamingEnumeration search(String name, Attributes matchingAttributes) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public NamingEnumeration search(Name name, String filter, SearchControls cons) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public NamingEnumeration search(String name, String filter, SearchControls cons) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public NamingEnumeration search(Name name, String filterExpr, Object[] filterArgs, SearchControls cons)
			throws NamingException {
		throw new OperationNotSupportedException();
	}

	public NamingEnumeration search(String name, String filterExpr, Object[] filterArgs, SearchControls cons)
			throws NamingException {
		throw new OperationNotSupportedException();
	}

	public String getNameInNamespace() throws NamingException {
		throw new OperationNotSupportedException();
	}
}
