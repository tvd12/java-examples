package com.tvd12.example.concurrent.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class FixRateAndFixDelay {

    public static void main(String[] args) {
        ScheduledExecutorService fixRate = Executors.newSingleThreadScheduledExecutor();
        AtomicLong fixRateStartTime = new AtomicLong();
        fixRate.scheduleAtFixedRate(() -> {
            long current = System.currentTimeMillis();
            if (fixRateStartTime.get() != 0) {
                long totalWaitTime = current - fixRateStartTime.get();
                System.out.println("fix rate - totalWaitTime: " + totalWaitTime/1000);
            }
            fixRateStartTime.set(current);
            sleep(5);
        }, 0, 10, TimeUnit.SECONDS);

        AtomicLong fixDelayTime = new AtomicLong();
        ScheduledExecutorService fixDelay = Executors.newSingleThreadScheduledExecutor();
        fixDelay.scheduleWithFixedDelay(() -> {
            long current = System.currentTimeMillis();
            if (fixDelayTime.get() != 0) {
                long totalWaitTime = current - fixDelayTime.get();
                System.out.println("fix delay - totalWaitTime: " + totalWaitTime/1000);
            }
            fixDelayTime.set(current);
            sleep(5);
        }, 0, 10, TimeUnit.SECONDS);
    }

    private static void sleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {}
    }
}
