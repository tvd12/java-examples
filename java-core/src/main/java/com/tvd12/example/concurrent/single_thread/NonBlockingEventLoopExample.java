package com.tvd12.example.concurrent.single_thread;

public class NonBlockingEventLoopExample {

    public static void main(String[] args) {
        NonBlockingEventLoop eventLoop = new NonBlockingEventLoop();
        Runnable updateViewEvent = new Runnable() {
            @Override
            public void run() {
                System.out.println("update state");
                eventLoop.addEvent(this);
            }
        };
        eventLoop.addEvent(updateViewEvent);
        eventLoop.onUpdate(() -> System.out.println("Update view"));
        eventLoop.start();
    }
}
