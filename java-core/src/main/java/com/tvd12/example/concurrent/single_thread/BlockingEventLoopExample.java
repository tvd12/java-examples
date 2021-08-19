package com.tvd12.example.concurrent.single_thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BlockingEventLoopExample {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        BlockingEventLoop eventLoop = new BlockingEventLoop();
        scheduler.scheduleAtFixedRate(() -> {
            eventLoop.addEvent(() -> {
                System.out.println("event's processing");
            });
        }, 0, 3, TimeUnit.SECONDS);
        eventLoop.start();
    }
}
