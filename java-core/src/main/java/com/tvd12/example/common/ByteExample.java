package com.tvd12.example.common;

public class ByteExample {

    public static void main(String[] args) {
        byte value = 0;
        value |= 1 << 0;
        value |= 1 << 1;
        value |= 1 << 2;
        value |= 1 << 3;
        value |= 1 << 4;
        value |= 1 << 5;
        value |= 1 << 6;
        value |= 1 << 7;
        System.out.println(value);
        System.out.println(value & 1 << 0);
        System.out.println(value & 1 << 1);
        System.out.println(value & 1 << 2);
        System.out.println(value & 1 << 3);
        System.out.println(value & 1 << 4);
        System.out.println(value & 1 << 5);
        System.out.println(value & 1 << 6);
        System.out.println(value & 1 << 7);

        System.out.println(2275053848343633295L >> 32);
    }

}
