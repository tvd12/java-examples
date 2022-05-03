package com.tvd12.example.jndi.my;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;

@SuppressWarnings("rawtypes")
public class MyInitialContextFactory implements InitialContextFactory {

    public static final MyContext CONTEXT = new MyContext(getEnviroment());

    private static Hashtable getEnviroment() {
        Hashtable env = new Hashtable<>();
        setProperty(env, Context.APPLET);
        setProperty(env, Context.AUTHORITATIVE);
        setProperty(env, Context.BATCHSIZE);
        setProperty(env, Context.DNS_URL);
        setProperty(env, Context.INITIAL_CONTEXT_FACTORY);
        setProperty(env, Context.LANGUAGE);
        setProperty(env, Context.OBJECT_FACTORIES);
        setProperty(env, Context.PROVIDER_URL);
        setProperty(env, Context.REFERRAL);
        setProperty(env, Context.SECURITY_AUTHENTICATION);
        setProperty(env, Context.SECURITY_CREDENTIALS);
        setProperty(env, Context.SECURITY_PRINCIPAL);
        setProperty(env, Context.SECURITY_PROTOCOL);
        setProperty(env, Context.STATE_FACTORIES);
        setProperty(env, Context.URL_PKG_PREFIXES);
        return env;
    }

    @SuppressWarnings("unchecked")
    private static void setProperty(Hashtable env, String name) {
        if (System.getProperties().containsKey(name)) {
            env.put(name, System.getProperties().getProperty(name));
        }
    }

    @Override
    public Context getInitialContext(Hashtable environment) throws NamingException {
        return CONTEXT;
    }

}
