package com.tvd12.example.concurrent.collection;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

public class BlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        LinkedList<String> list = new LinkedList<>();
        System.out.println(list.poll());
        LinkedBlockingDeque<String> queue = new LinkedBlockingDeque<>();
        Thread thread = new Thread(() -> {
            String str;
            try {
                while (true) {
                    str = queue.takeLast();
                    queue.addLast(str);
                    System.out.println("str = " + str + ", size = " + queue.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        Thread thread2 = new Thread(() -> {
            queue.add("hello");
        });
        thread2.start();
        Thread.sleep(3);
        Thread thread3 = new Thread(() -> {
            queue.add("world");
        });
        thread3.start();
        Thread.sleep(3000);
    }

}
