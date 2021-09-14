package com.tvd12.example.concurrent.single_thread;

public class SingleThread {
    public static void main(String[] args) {
        NonBlockingEventLoop eventLoop = new NonBlockingEventLoop(3000);
        eventLoop.onUpdate(() -> {
            eventLoop.addEvent(() -> System.out.println("Hello World"));
        });
        eventLoop.start();
    }
}
