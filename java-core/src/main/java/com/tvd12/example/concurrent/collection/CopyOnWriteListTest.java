package com.tvd12.example.concurrent.collection;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteListTest {

    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>();
        Thread[] readThreads = new Thread[10];
        Thread[] writeThreads = new Thread[10];
        for (int i = 0 ; i < readThreads.length ; ++i) {
            readThreads[i] = new Thread(() -> {
                while (true) {
                    list.add(1);
                }
            });
            writeThreads[i] = new Thread(() -> {
                while (true) {
                    for (Integer value : list) ;
                }
            });
        }
        for (int i = 0 ; i < readThreads.length ; ++i) {
            readThreads[i].start();
            writeThreads[i].start();
        }
    }
}
