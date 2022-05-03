package com.tvd12.example.serializer;

import java.io.*;
import java.util.Arrays;

import static java.io.ObjectStreamConstants.PROTOCOL_VERSION_2;

public class JavaSerializerExample {

    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
//        A a = new A();
//        a.value = "Hello";
//        out.writeObject(a);

//        byte[] bytes = byteOut.toByteArray();

//        System.out.println(Arrays.toString(bytes));

        byte[] bytes = new byte[]{-84, -19, 0, 5, 115, 114, 0, 52, 99, 111, 109, 46, 116, 118, 100, 49, 50, 46, 101, 120, 97, 109, 112, 108, 101, 46, 115, 101, 114, 105, 97, 108, 105, 122, 101, 114, 46, 74, 97, 118, 97, 83, 101, 114, 105, 97, 108, 105, 122, 101, 114, 69, 120, 97, 109, 112, 108, 101, 36, 65, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 1, 76, 0, 5, 118, 97, 108, 117, 101, 116, 0, 18, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 120, 112, 116, 0, 5, 72, 101, 108, 108, 111};

        ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(byteIn);

        A b = (A) in.readObject();
        System.out.println(b);
    }

    public static class A implements Serializable {
        private static final long serialVersionUID = 3L;

        public String value;
        public String hack;
    }
}
