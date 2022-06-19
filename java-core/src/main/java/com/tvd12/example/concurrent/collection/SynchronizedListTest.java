package com.tvd12.example.concurrent.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedListTest {

    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(
            new ArrayList<>()
        );
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
//                    for (int k = 0 ; k < list.size() ; ++k) ;
                }
            });
        }
        for (int i = 0 ; i < readThreads.length ; ++i) {
            readThreads[i].start();
            writeThreads[i].start();
        }
    }
}
