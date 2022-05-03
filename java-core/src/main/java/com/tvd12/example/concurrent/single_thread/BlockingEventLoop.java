package com.tvd12.example.concurrent.single_thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingEventLoop {
    private final static Runnable FINISH_EVENT = () -> {};
    private final BlockingQueue<Runnable> eventQueue;
    private volatile boolean active;

    public BlockingEventLoop() {
        this(new LinkedBlockingQueue<>());
    }

    public BlockingEventLoop(
        BlockingQueue<Runnable> eventQueue
    ) {
        this.eventQueue = eventQueue;
    }

    public void addEvent(Runnable event) {
        eventQueue.offer(event);
    }

    public void start() {
        Throwable exception = null;
        this.active = true;
        while (active) {
            try {
                Runnable event = eventQueue.take();
                if (event == FINISH_EVENT) {
                    break;
                }
                event.run();
            } catch (Throwable e) {
                exception = e;
                active = false;
            }
        }
        if (exception != null) {
            throw new IllegalStateException(exception);
        }
    }

    public void stop() {
        this.active = false;
        this.eventQueue.offer(FINISH_EVENT);
    }
}
