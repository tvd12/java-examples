package com.tvd12.example.concurrent.async;

import java.util.LinkedList;
import java.util.Queue;

public class EventLoop {
    public static void main(String[] args) throws Exception {
        Queue<Runnable> queue = new LinkedList<>();
        while (true) {
            Thread.sleep(3);
            while (queue.size() > 0) {
                final Runnable task = queue.poll();
                process(task);
            }
        }
    }

    private static void process(Runnable task) {
        task.run();
    }
}
