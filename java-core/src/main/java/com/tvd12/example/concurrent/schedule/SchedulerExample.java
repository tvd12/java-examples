package com.tvd12.example.concurrent.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerExample {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService =
            Executors.newScheduledThreadPool(5);
        scheduledExecutorService.schedule(() -> {
            System.out.println("show notifcation");
        }, 5, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("show notifcation");
        }, 10, 20, TimeUnit.SECONDS);
    }

}
