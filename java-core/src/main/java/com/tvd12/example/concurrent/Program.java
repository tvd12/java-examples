package com.tvd12.example.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Program {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = new ExecutorService(1, 10);
        for (int i = 0; i < 100; ++i) {
            executorService.execute(() -> {
                System.out.println("current thread: " + Thread.currentThread().getName());
            });
        }
        executorService.shutdown();
    }

    public static class ExecutorService {
        private static final Runnable POISON = () -> {};
        private final int maxThreads;
        private final BlockingQueue<Runnable> taskQueue;
        private final List<Thread> threads = new ArrayList<>();


        public ExecutorService(int minThreads, int maxThreads) {
            this.maxThreads = maxThreads;
            this.taskQueue = new LinkedBlockingQueue<>();
            for (int i = 0; i < minThreads; ++i) {
                Thread thread = new Thread(this::loop);
                thread.setName("executor-thread-" + i);
                threads.add(thread);
                thread.start();
            }
        }

        private void loop() {
            while (true) {
                try {
                    Runnable task = taskQueue.take();
                    if (task == POISON) {
                        break;
                    }
                    task.run();
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

        private void execute(Runnable task) {
            taskQueue.add(task);
            int queueSize = taskQueue.size();
            synchronized (threads) {
                if (queueSize > 0 && threads.size() < maxThreads) {
                    Thread thread = new Thread(this::loop);
                    thread.setName("executor-thread-" + threads.size());
                    threads.add(thread);
                    thread.start();
                }
            }
        }

        public void shutdown() {
            for (int i = 0; i < threads.size(); ++i) {
                taskQueue.add(POISON);
            }
        }

        public void shutdownNow() {
            for (Thread thread : threads) {
                thread.interrupt();
            }
        }
    }
}
