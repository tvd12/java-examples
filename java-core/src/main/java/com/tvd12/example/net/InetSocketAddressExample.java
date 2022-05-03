package com.tvd12.example.net;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

public class InetSocketAddressExample {

    public static void main(String[] args) {
        InetSocketAddress a1 = new InetSocketAddress("127.0.0.1", 3005);
        InetSocketAddress a2 = new InetSocketAddress("127.0.0.1", 3005);
        InetSocketAddress a3 = new InetSocketAddress("127.0.0.2", 3005);
        InetSocketAddress a4 = new InetSocketAddress("127.0.0.1", 3006);
        InetSocketAddress a5 = new InetSocketAddress("localhost", 3005);
        System.out.println(a1.equals(a2));
        System.out.println(a1.equals(a3));
        System.out.println(a1.equals(a4));
        System.out.println(a3.equals(a4));
        System.out.println("1-5: " + a1.equals(a5));

        System.out.println(a1.hashCode() == a2.hashCode());
        System.out.println(a1.hashCode() == a3.hashCode());
        System.out.println(a1.hashCode() == a4.hashCode());
        System.out.println(a3.hashCode() == a4.hashCode());

        Set<InetSocketAddress> set = new HashSet<>();
        set.add(a1);
        set.add(a2);
        set.add(a3);
        set.add(a4);
        System.out.println(set.size());
        System.out.println(set);
    }

}
