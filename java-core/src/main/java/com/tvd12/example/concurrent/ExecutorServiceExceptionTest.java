package com.tvd12.example.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExceptionTest {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            throw new RuntimeException("test1");
        });
        executorService.execute(() -> {
            throw new RuntimeException("test2");
        });
        Thread.sleep(300);
        executorService.execute(() -> {
            System.out.println("hello world");
        });
    }
}
