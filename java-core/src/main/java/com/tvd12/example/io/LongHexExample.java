package com.tvd12.example.io;

public class LongHexExample {

    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.toHexString(Long.MAX_VALUE));
        System.out.println(Long.toString(Long.MAX_VALUE, Character.MAX_RADIX));
        System.out.println(Long.parseLong("1y2p0ij32e8e7", Character.MAX_RADIX));
        System.out.println(((Long.MAX_VALUE / 2_000_000_000)) / (365 * 24 * 60 * 60));

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; ++i) {
            Long.toString(Long.MAX_VALUE, Character.MAX_RADIX);
        }
        long offset = System.currentTimeMillis() - start;
        System.out.println(offset);
    }

}
