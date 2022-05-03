package com.tvd12.example.concurrent.sync;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanExample {

    private AtomicBoolean active = new AtomicBoolean(false);

    private static void sleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        AtomicBooleanExample example = new AtomicBooleanExample();
        example.prepare();
        example.start();
        sleep(10);
    }

    public void prepare() throws InterruptedException {
        new Thread(() -> {
            System.out.println("application preparing ...");
            sleep(3);
            active.set(true);
        })
            .start();
    }

    public void start() throws Exception {
        new Thread(() -> {
            while (!active.get()) ;
            System.out.println("application started");
        })
            .start();
    }
}
