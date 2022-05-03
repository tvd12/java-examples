package com.tvd12.example.ldap.ex1;

import javax.naming.*;
import java.util.Hashtable;
import javax.naming.spi.ObjectFactory;

/**
 * This class is the Factory used to recreate the Apartement objects from the Reference.
 */

public class ApartmentFactory implements ObjectFactory {

    public ApartmentFactory() {}

    @SuppressWarnings({"rawtypes"})
    public Object getObjectInstance(Object obj, Name name,
                                    Context ctx, Hashtable env) throws Exception {

        Reference ref = (Reference) obj;
        RefAddr addr = ref.get("Apartment details");
        String temp = (String) addr.getContent();
        int offset = temp.indexOf(":");
        String size = temp.substring(0, offset);
        String location = temp.substring(offset + 1);
        return (new Apartment(size, location));
    }
}

