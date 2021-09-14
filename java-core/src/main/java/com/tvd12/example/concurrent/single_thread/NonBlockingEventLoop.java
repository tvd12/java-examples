
package com.tvd12.example.concurrent.single_thread;

import java.util.LinkedList;
import java.util.Queue;

public class NonBlockingEventLoop {
    private volatile boolean active;
    private final long sleepTime;
    private Runnable onUpdateCallback;
    private final Queue<Runnable> eventQueue;
    private static final long DEFAULT_SLEEP_TIME = 3;

    public NonBlockingEventLoop() {
        this(DEFAULT_SLEEP_TIME);
    }

    public NonBlockingEventLoop(long sleepTime) {
        this.sleepTime = sleepTime;
        this.eventQueue = new LinkedList<>();
    }

    public void addEvent(Runnable event) {
        synchronized (eventQueue) {
            eventQueue.offer(event);
        }
    }

    public void onUpdate(Runnable callback) {
        this.onUpdateCallback = callback;
    }

    public void start() {
        Throwable exception = null;
        Queue<Runnable> buffer = new LinkedList<>();
        this.active = true;
        while (active) {
            try {
                long nextTime = System.currentTimeMillis() + sleepTime;
                synchronized (eventQueue) {
                    while (!eventQueue.isEmpty()) {
                        buffer.add(eventQueue.poll());
                    }
                }
                while (!buffer.isEmpty()) {
                    Runnable event = buffer.poll();
                    event.run();
                }
                if(onUpdateCallback != null) {
                    onUpdateCallback.run();
                }
                long currentTime = System.currentTimeMillis();
                if(currentTime < nextTime) {
                    long offset = nextTime - currentTime;
                    Thread.sleep(offset);
                }
            }
            catch (Throwable e) {
                exception = e;
                active = false;
            }
        }
        if(exception != null) {
            throw new IllegalStateException(exception);
        }
    }

    public void stop() {
        this.active = false;
    }
}