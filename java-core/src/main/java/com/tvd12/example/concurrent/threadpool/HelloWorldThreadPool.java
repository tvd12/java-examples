package com.tvd12.example.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelloWorldThreadPool {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() ->
            System.out.println(Thread.currentThread().getName() + ": Hello World")
        );
        Thread.sleep(1000);
    }
}
