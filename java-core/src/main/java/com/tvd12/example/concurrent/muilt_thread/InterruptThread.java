package com.tvd12.example.concurrent.muilt_thread;

public class InterruptThread {

    public static void main(String[] args) throws Exception {
        Thread newThread = new Thread(() -> {
           while (true) {
               System.out.println("current thread: " + Thread.currentThread().getName());
           }
        });
        newThread.start();
        Thread.sleep(10);
        newThread.interrupt();
        while (true) {
            Thread.sleep(10);
        }
    }
}
