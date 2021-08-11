package com.tvd12.example.concurrent.schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {

    public static void main(String[] args) throws Exception {
        System.out.println("bat dau chay" + Calendar.getInstance());
        Callable<String> callable1 = new Callable() {
            @Override
            public String call() throws Exception {
                System.out.println("task 1");
                return "task --1";
            }
        };
        Callable<String> callable2 = new Callable() {
            @Override
            public String call() throws Exception {
                System.out.println("task 2");
                return "task --2";
            }
        };
        Callable<String> callable3 = new Callable() {
            @Override
            public String call() throws Exception {
                System.out.println("task 3");
                return "task --3";
            }
        };
        List<Callable<String>> callables = new ArrayList<>();
        callables.add(callable1);
        callables.add(callable2);
        callables.add(callable3);
        callables.add(callable1);
        callables.add(callable2);
        callables.add(callable3);
        callables.add(callable1);
        callables.add(callable2);
        callables.add(callable3);

//        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        List<Future<String>> futures = executorService.invokeAll(callables);
        for (Future<String> f : futures) {
            System.out.println(f.get());
        }
        System.out.println("ket thuc" + Calendar.getInstance());
        executorService.shutdown();
    }

}
