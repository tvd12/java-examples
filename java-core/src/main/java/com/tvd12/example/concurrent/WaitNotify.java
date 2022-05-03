package com.tvd12.example.concurrent;

import java.util.LinkedList;
import java.util.Random;

public class WaitNotify {

    public static void main(String[] args) {
        BlockingQueue<String> queue = new BlockingQueue<>();
        Thread producer = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.offer(String.valueOf(new Random().nextInt()));
            }
        });
        Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.start();
        consumer.start();
    }

    public static class BlockingQueue<E> {
        private final LinkedList<E> items = new LinkedList<>();

        public void offer(E item) {
            synchronized (items) {
                items.offer(item);
                items.notify();
            }
        }

        public E take() throws InterruptedException {
            synchronized (items) {
                while (items.isEmpty()) {
                    items.wait();
                }
                return items.poll();
            }
        }
    }
}
