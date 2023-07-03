package com.tvd12.example.collection;

import java.net.URL;
import java.util.ArrayDeque;
import java.util.Deque;

public class DequeExample {

    public static void main(String[] args) throws Exception {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.push(1);
        deque.push(2);
        System.out.println(deque.pop());
        System.out.println(deque.pop());

        double num1 = 0.1;
        double num2 = 0.2;
        double incorrectSum = num1 + num2;

        System.out.println("Incorrect Sum: " + incorrectSum);

        URL url = new URL("https://v2.youngmonkeys.org/posts/deploy-ezyfox-server/preview");
        System.out.println(url.getHost());
    }
}
