package com.tvd12.example.jndi.my1;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

import javax.naming.Binding;
import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NameClassPair;
import javax.naming.NameNotFoundException;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.NotContextException;
import javax.naming.OperationNotSupportedException;
import javax.naming.spi.NamingManager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class MyContext implements Context {

	protected MyContext parent;
	protected String atomicName;
	protected Hashtable bindings = new Hashtable<>(11);
	protected Hashtable environment = new Hashtable<>();
	
	protected static final NameParser NAME_PARSER = new MyNameParser();
	
	public MyContext(Hashtable environment) {
		if(environment != null)
			environment.putAll(environment);
	}
	
	protected MyContext(MyContext parent, String name, Hashtable env, Hashtable bindings) {
		this(env);
		this.parent = parent;
		this.atomicName = name;
		this.bindings.putAll(bindings);
	}
	
	protected Context createContext(MyContext parent, String name, Hashtable env) {
		return new MyContext(parent, name, env, new Hashtable<>());
	}
	
	protected Context duplicate() {
		return new MyContext(parent, atomicName, environment, bindings);
	}
	
	/**
	 * Utility method for processing composite/compound name.
	 * 
	 * @param name The non-null composite or compound name to process.
	 * @return The non-null string name in this namespace to be processed.
	 */
	protected Name getComponents(Name name) throws NamingException {
		return name;
		// Already parsed
//		if(!(name instanceof CompositeName))
//			return name;
		
//		// If is complex name
//		if(name.size() > 1)
//			throw new InvalidNameException(name.toString() + " has more components than namespace can handle");
//		
//		// Turn component that belongs to us into compound name
//		return NAME_PARSER.parse(name.get(0));
	}
	
	protected NamingException newNamingException(String msg, Throwable e) {
		NamingException exception = new NamingException(msg);
		exception.setRootCause(e);
		return exception;
	}
	
	@Override
	public Object lookup(String name) throws NamingException {
		return lookup(new CompositeName(name));
	}
	
	@Override
	public Object lookup(Name name) throws NamingException {
		// Asking to look up this context itself. Create and return
		// a new instance with its own independent environment.
		if(name.isEmpty())
			return duplicate();
	
		// Extract components that belong to this namespace
		Name nm = getComponents(name);
		String atom = nm.get(0);
		Object inter = bindings.get(atom);
		
		if(nm.size() == 1) {
			// Atomic name: Find object in internal data structure
			if(inter == null)
				throw new NameNotFoundException(name + " not found");
			
			// Call getObjectInstance for using any object factories
			try {
				return NamingManager.getObjectInstance(inter, new CompositeName().add(atom), this, environment);
			} catch (Exception e) {
				throw newNamingException(atom + " does not name a context", e);
			}
		}
		else {
			// Intermediate name: Consume name in this context and continue
			if(!(inter instanceof Context))
				throw new NotContextException(atom + " does not name a context");
			return ((Context)inter).lookup(nm.getSuffix(1));
		}
	}
	
	@Override
	public void bind(String name, Object obj) throws NamingException {
		bind(new CompositeName(name), obj);
	}

	@Override
	public void bind(Name name, Object obj) throws NamingException {
		if(name.isEmpty())
			throw new InvalidNameException("Cannot bind empty name");
		
		// Extract components that belong to this namespace
		Name nm = getComponents(name);
		String atom = nm.get(0);
		Object inter = bindings.get(atom);
		
		if(nm.size() == 1) {
			// Atomic name: Find object in internal data structure
			if(inter != null)
				throw new NameAlreadyBoundException("Use rebind to override");
			
			// Call getStateToBind for using any state factories
			Object valid = NamingManager.getStateToBind(obj, new CompositeName().add(atom), this, environment);
			
			// Add object to internal data structure
			bindings.put(atom, valid);
		}
		else {
			// Intermediate name: consume name in this context and continue
			if(!(inter instanceof Context))
				throw new NotContextException(atom + " does not name a context");
			((Context)inter).bind(nm.getSuffix(1), obj);
		}
	}
	
	@Override
	public void rebind(String name, Object obj) throws NamingException {
		rebind(new CompositeName(name), obj);
	}

	@Override
	public void rebind(Name name, Object obj) throws NamingException {
		if(name.isEmpty())
			throw new InvalidNameException("Cannot bind empty name");
		
		// Extract components that belong to this namespace
		Name nm = getComponents(name);
		String atom = nm.get(0);
		
		if(nm.size() == 1) {
			// Atomic name
			
			// Call getStateBind for using any state factories
			Object valid = NamingManager.getStateToBind(obj, new CompositeName().add(atom), this, environment);
			
			// Add object to internal data structure
			bindings.put(atom, valid);
		}
		else {
			// Intermediate name: consume nam in this context and continue
			Object inter = bindings.get(atom);
			if(!(inter instanceof Context)) 
				throw new NotContextException(atom + " does not name a context");
			((Context)inter).rebind(nm.getSuffix(1), obj);
		}
	}
	
	@Override
	public void unbind(String name) throws NamingException {
		unbind(new CompositeName(name));
	}
	
	@Override
	public void unbind(Name name) throws NamingException {
		if(name.isEmpty())
			throw new InvalidNameException("Cannot unbind empty name");
		
		// Extract components that belong to this namespace
		Name nm = getComponents(name);
		String atom = nm.get(0);
		
		// Remove object from internal data structure
		if(nm.size() == 1) {
			// Atomic name: find object in internal data structure
			bindings.remove(atom);
		}
		else {
			// Intermediate name: consume name in this context and continue
			Object inter = bindings.get(atom);
			if(!(inter instanceof Context))
				throw new NotContextException(atom + " doest not name a context");
			((Context) inter).unbind(nm.getSuffix(1));
		}
	}
	
	@Override
	public void rename(String oldname, String newname) throws NamingException {
		rename(new CompositeName(oldname), new CompositeName(newname));
	}

	@Override
	public void rename(Name oldname, Name newname) throws NamingException {
		if(oldname.isEmpty() || newname.isEmpty())
			throw new InvalidNameException("Cannot rename empty name");
		
		// Extract components that belong to this namespace
		Name oldnm = getComponents(oldname);
		Name newnm = getComponents(newname);
		
		// Simplistic implementation: support only rename within same context
		if(oldnm.size() != newnm.size())
			throw new OperationNotSupportedException("Do not support rename across different contexts");
		
		String oldatom = oldnm.get(0);
		String newatom = newnm.get(0);
		
		if(oldnm.size() == 1) {
			// Atomic name: Add object to internal data structure
			// Check if new name exists
			if(bindings.get(newatom) != null)
				throw new NameAlreadyBoundException(newname.toString() + " is already bound");
			
			// Check if old name is bound
			Object oldBinding = bindings.remove(oldatom);
			if(oldBinding == null)
				throw new NameNotFoundException(oldname.toString() + " not found");
			bindings.put(newatom, oldBinding);
		}
		else {
			// Simplistic implementation: support only rename within same context
			if(!oldname.equals(newatom))
				throw new OperationNotSupportedException("Do not support rename across different contexts");
			
			// Intermediate name: consume name in this context and continue
			Object inter = bindings.get(oldatom);
			if(!(inter instanceof Context))
				throw new NotContextException(oldatom + " doest not name a context");
			((Context) inter).rename(oldnm.getSuffix(1), newnm.getSuffix(1));
		}
	}
	
	@Override
	public NamingEnumeration<NameClassPair> list(String name) throws NamingException {
		return list(new CompositeName(name));
	}

	@Override
	public NamingEnumeration<NameClassPair> list(Name name) throws NamingException {
		// listing this context
		if(name.isEmpty())
			return new MyListOfNames(bindings.keys());
		
		// Perhaps 'name' names a context
		Object target = lookup(name);
		if(target instanceof Context)
			return ((Context)target).list("");
		throw new NotContextException(name + " cannot be listed");
	}

	@Override
	public NamingEnumeration<Binding> listBindings(String name) throws NamingException {
		return listBindings(new CompositeName(name));
	}
	
	@Override
	public NamingEnumeration<Binding> listBindings(Name name) throws NamingException {
		// listing this context
		if(name.isEmpty())
			return new MyListOfBindings(bindings.keys());
		
		// Perhaps 'name' names a context
		Object target = lookup(name);
		if(target instanceof Context)
			return ((Context)target).listBindings("");
		throw new NotContextException(name + " cannot be listed");
	}
	
	@Override
	public void destroySubcontext(String name) throws NamingException {
		destroySubcontext(new CompositeName(name));
	}

	@Override
	public void destroySubcontext(Name name) throws NamingException {
		if(name.isEmpty())
			throw new InvalidNameException("Cannot destroy context using empty name");
		
		// Simplistic implementation: not checking for nonempty context first
		// Use same implementation as unbind
		unbind(name);
	}
	
	@Override
	public Context createSubcontext(String name) throws NamingException {
		return createSubcontext(new CompositeName(name));
	}

	@Override
	public Context createSubcontext(Name name) throws NamingException {
		if(name.isEmpty())
			throw new InvalidNameException("Cannot bind empty name");
		
		// Extract components that belong to thi namespace
		Name nm = getComponents(name);
		String atom = nm.get(0);
		Object inter = bindings.get(atom);
		
		if(nm.size() == 1) {
			// Atomic name: find object in internal data structure
			if(inter != null)
				throw new NameAlreadyBoundException("Use rebind to override");
			
			// Create child
			Context child = createContext(this, atom, environment);
			
			// Add child to internal data structure
			bindings.put(atom, child);
			
			return child;
		}
		else {
			// Intermediate name: consume name in this context and continue
			if(!(inter instanceof Context))
				throw new NotContextException(atom + " does not name a context");
			return ((Context)inter).createSubcontext(nm.getSuffix(1));
		}
	}

	@Override
	public Object lookupLink(String name) throws NamingException {
		return lookupLink(new CompositeName(name));
	}
	
	@Override
	public Object lookupLink(Name name) throws NamingException {
		return lookup(name);
	}
	
	@Override
	public NameParser getNameParser(String name) throws NamingException {
		return getNameParser(new CompositeName(name));
	}

	@Override
	public NameParser getNameParser(Name name) throws NamingException {
		// Do lookup to verify name exists
		Object obj = lookup(name);
		if(obj instanceof Context)
			((Context)obj).close();
		return NAME_PARSER;
	}
	
	@Override
	public String composeName(String name, String prefix) throws NamingException {
		Name result = composeName(new CompositeName(name), new CompositeName(prefix));
		return result.toString();
	}

	@Override
	public Name composeName(Name name, Name prefix) throws NamingException {
		// Both are compound names, compose using compound name rules
		if(!(name instanceof CompositeName) && !(prefix instanceof CompositeName)) {
			Name result = (Name)(prefix.clone());
			result.addAll(name);
			return new CompositeName().add(result.toString());
		}
		//Simplistic implementation: do not support federation
		throw new OperationNotSupportedException("Do not support composing composite name");
	}

	@Override
	public Object addToEnvironment(String propName, Object propVal) throws NamingException {
		return environment.put(propName, propVal);
	}

	@Override
	public Object removeFromEnvironment(String propName) throws NamingException {
		return environment.remove(propName);
	}

	@Override
	public Hashtable<?, ?> getEnvironment() throws NamingException {
		return new Hashtable<>(environment);
	}


	@Override
	public String getNameInNamespace() throws NamingException {
		MyContext ancestor = parent;
		
		// No ancestor
		if(ancestor == null)
			return "";
		Name name = NAME_PARSER.parse("");
		name.add(atomicName);
		
		// Get parent's names
		while(ancestor != null && ancestor.atomicName != null) {
			name.add(0, ancestor.atomicName);
			ancestor = ancestor.parent;
		}
		return name.toString();
	}
	
	@Override
	public void close() throws NamingException {
		
	}
	
	@Override
	public String toString() {
		return atomicName != null ? atomicName : "ROOT CONTEXT";
	}

	//============= new class ==============
	// Class for enumerating name/class pairs
	class MyListOfNames implements NamingEnumeration {
		
		protected Enumeration names;
		
		MyListOfNames(Enumeration names) {
			this.names = names;
		}

		@Override
		public boolean hasMoreElements() {
			try {
				return hasMore();
			}
			catch(NamingException e) {
				return false;
			}
		}
		
		@Override
		public boolean hasMore() throws NamingException {
			return names.hasMoreElements();
		}
		
		@Override
		public Object next() throws NamingException {
			String name = (String)names.nextElement();
			String className = bindings.get(name).getClass().getName();
			return new NameClassPair(name, className);
		}

		@Override
		public Object nextElement() {
			try {
				return next();
			}
			catch(NamingException e) {
				throw new NoSuchElementException(e.toString());
			}
		}

		@Override
		public void close() throws NamingException {
		}
		
	}
	
	//============= new class ==============
	// Class for enumerating bindings
	class MyListOfBindings extends MyListOfNames {
		
		MyListOfBindings(Enumeration names) {
			super(names);
		}
		
		@Override
		public Object next() throws NamingException {
			String name = (String) names.nextElement();
			Object obj = bindings.get(name);
			
			try {
				obj = NamingManager.getObjectInstance(obj, new CompositeName().add(name), MyContext.this, environment);
			}
			catch(Exception e) {
				throw newNamingException("getObjectInstance failed", e);
			}
			
			return new Binding(name, obj);
		}
		
	}
	
}
