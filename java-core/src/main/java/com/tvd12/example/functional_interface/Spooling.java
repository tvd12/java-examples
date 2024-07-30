package com.tvd12.example.functional_interface;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Spooling {

    public static void main(String[] args) {
        BlockingQueue<String> fileQueue = new LinkedBlockingQueue<>();
        Thread printThread = new Thread(() -> {
            while (true) {
                try {
                    String file = fileQueue.take();
                    System.out.println("print: " + file);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        printThread.setName("print-thread");
        printThread.start();
        Thread computer1 = new Thread(() -> {
            fileQueue.add("File 1");
        });
        Thread computer2 = new Thread(() -> {
            fileQueue.add("File 2");
        });
        Thread computer3 = new Thread(() -> {
            fileQueue.add("File 3");
        });
        computer1.start();
        computer2.start();
        computer3.start();
    }
}
