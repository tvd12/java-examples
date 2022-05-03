package com.tvd12.example.io;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapForEachTest {

    public static void main(String[] args) throws Exception {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 10000; i++) {
            map.put(String.valueOf(i), String.valueOf(i));
        }
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                map.keySet().forEach(s -> {
                    Random random = new Random();
                    boolean remove = random.nextBoolean();
                    if (remove) {
                        map.remove(s);
                    } else {
                        map.put(s, s);
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            });
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }

}
