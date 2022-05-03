package com.tvd12.example.concurrent.sync;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VolatileIntExample {

    private final Map map = new ConcurrentHashMap();
    private volatile int count;

    public static void main(String[] args) throws Exception {
        VolatileIntExample example = new VolatileIntExample();
        example.start();
        Thread.sleep(3000);
        System.out.println(example.map.size());
    }

    public void start() {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread(() -> {
                while (count <= 1000000) {
                    ++count;
                    map.put(Integer.valueOf(count), Integer.valueOf(count));
                }
            });
        }
        for (int i = 0; i < threads.length; ++i) {
            threads[i].start();
        }
    }
}