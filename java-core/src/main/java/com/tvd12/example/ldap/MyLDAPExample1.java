package com.tvd12.example.ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

@SuppressWarnings({"rawtypes", "unchecked"})
public class MyLDAPExample1 {

    public static void main(String[] args) throws NamingException {
        new MyLDAPExample1().newContext();
    }

    protected DirContext newContext() throws NamingException {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:10389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "uid=admin;ou=system"); // specify the username
        env.put(Context.SECURITY_CREDENTIALS, "123456");           // specify the password
        return new InitialDirContext(env);
    }

}
