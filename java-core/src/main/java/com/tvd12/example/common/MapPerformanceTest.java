package com.tvd12.example.common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapPerformanceTest {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> cmap = new ConcurrentHashMap<>();
        for (int i = 0; i < 1000000; ++i) {
            map.put(i, i);
            cmap.put(i, i);
        }
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            map.get(-1);
        }
        long offset1 = System.currentTimeMillis() - start1;

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            cmap.get(-1);
        }
        long offset2 = System.currentTimeMillis() - start2;

        System.out.print("map.p = " + offset1 + ", cmap.p = " + offset2);
    }

}
