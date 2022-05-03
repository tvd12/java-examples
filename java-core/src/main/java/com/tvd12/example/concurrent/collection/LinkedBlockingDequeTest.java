package com.tvd12.example.concurrent.collection;

import java.util.ArrayDeque;

public class LinkedBlockingDequeTest {

    public static void main(String[] args) {
        ArrayDeque<Object> arrayDeque = new ArrayDeque<>();
        arrayDeque.add(10);
        arrayDeque.add(20);
        System.out.println(arrayDeque.getFirst());
        System.out.println(arrayDeque.getLast());
    }

}
