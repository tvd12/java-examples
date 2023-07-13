package com.tvd12.example.io;

public class NewBytesPerformance {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0 ; i < 1000000 ; ++i) {
            byte[] bytes = new byte[17000];
        }
        long time = System.currentTimeMillis() - start;
        System.out.println(time);
    }
}
