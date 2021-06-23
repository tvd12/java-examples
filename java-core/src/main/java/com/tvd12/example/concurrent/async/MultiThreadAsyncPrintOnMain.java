package com.tvd12.example.concurrent.async;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class MultiThreadAsyncPrintOnMain {
    private static Async async = new Async();
    private static Queue<Runnable> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws Exception {
        final Runnable task1 = () -> {
            print("1 ");
            async.run(() -> {
                sleep(3000);
                print("4 ");
            });
        };
        final Runnable task2 = () -> {
            print("2 ");
            async.run(() -> {
                print("3 ");
            });
        };
        queue.add(task1);
        queue.add(task2);
        while(true) {
            Thread.sleep(3);
            while(queue.size() > 0) {
                final Runnable task = queue.poll();
                process(task);
            }
        }
    }

    private static void process(Runnable task) {
        task.run();
    }

    private static class Async {
        private ExecutorService executorService = Executors.newFixedThreadPool(8);
        void run(Runnable task) {
            executorService.execute(task);
        }
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) { }
    }

    private static void print(String message) {
        queue.add(() -> {
            final String threadName = Thread.currentThread().getName();
            System.out.print("(thread: " + threadName + ")" + message);
        });
    }
}
