package com.tvd12.example.reflection;

public class InstanceOfPerformance {

    public static void main(String[] args) {
        System.out.println(Object.class.isAssignableFrom(Object.class));
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; ++i) {
            if (Object.class.isAssignableFrom(IllegalArgumentException.class)) ;
        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.println(elapsed);
    }

}
