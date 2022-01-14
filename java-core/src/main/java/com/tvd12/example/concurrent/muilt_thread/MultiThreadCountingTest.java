package com.tvd12.example.concurrent.muilt_thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadCountingTest {
    static AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) throws Exception {
        int threadCount = 50;
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            Thread th = new Thread(() -> {
                for (int i1 = 0; i1 < 200000; i1++) {
                    count.incrementAndGet();
                }
            });
            th.start();
            list.add(th);
        }
        for (Thread thread : list) {
            thread.join();
        }
        System.out.println("Count: "+count);
    }
}