package com.tvd12.example.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LoopForeverProgram {

    public static void main(String[] args) {
        ScheduledExecutorService executorService =
            Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> {
            System.out.println("do something");
        }, 1000, TimeUnit.MILLISECONDS);
    }
}
