package com.tvd12.example.concurrent.single_thread;

public class EventLoop {
    private volatile boolean active;

    public void run(long sleepTime, Runnable runnable) {
        Throwable exception = null;
        this.active = true;
        while (active) {
            try {
                long nextTime = System.currentTimeMillis() + sleepTime;
                runnable.run();
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
