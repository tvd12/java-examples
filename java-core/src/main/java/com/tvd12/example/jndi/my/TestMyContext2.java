package com.tvd12.example.jndi.my;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.naming.spi.InitialContextFactoryBuilder;
import javax.naming.spi.NamingManager;
import javax.naming.spi.ObjectFactory;
import javax.naming.spi.ObjectFactoryBuilder;

import com.tvd12.example.jndi.MyObject;

public class TestMyContext2 {

    public static void main(String[] args) {
        try {
            NamingManager.setObjectFactoryBuilder(new ObjectFactoryBuilder() {

                @Override
                public ObjectFactory createObjectFactory(Object obj, Hashtable<?, ?> environment) throws NamingException {
                    System.out.println("obj = " + obj + ", env = " + environment);
                    return new ObjectFactory() {

                        @Override
                        public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)
                            throws Exception {
                            System.out.println("obj = " + obj + ", name = " + name + ", ctx = " + nameCtx + ", env = " + environment);
                            return obj;
                        }
                    };
                }
            });
            NamingManager.setInitialContextFactoryBuilder(new InitialContextFactoryBuilder() {

                @Override
                public InitialContextFactory createInitialContextFactory(Hashtable<?, ?> environment) throws NamingException {
                    System.out.println("env = " + environment);
                    return new MyInitialContextFactory();
                }
            });
            System.setProperty(Context.PROVIDER_URL, "tvd12:/");
            Context ctx = new InitialContext();
            MyObject myObject = new MyObject();
            ctx.bind("m", myObject);

            Context context = new InitialContext();
            System.out.println(context.lookup("m"));
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

}
