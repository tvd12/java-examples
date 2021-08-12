package com.tvd12.example.concurrent.sync;

public class CustomVolatileExample {

    private final Volatile<Boolean> active = new Volatile<>(false);

    public void prepare() throws InterruptedException {
        new Thread(() -> {
            System.out.println("application preparing ...");
            sleep(3);
            active.set(true);
        })
            .start();
    }

    public void start() throws Exception {
        new Thread(() -> {
            while(!active.get());
            System.out.println("application started");
        })
            .start();
    }

    private static void sleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        CustomVolatileExample example = new CustomVolatileExample();
        example.prepare();
        example.start();
        sleep(10);
    }
}

class Volatile<T> {
    private boolean changed;
    private T value;

    public Volatile(T intValue) {
        this.value = intValue;
    }

    public synchronized void set(T newValue) {
        value = newValue;
        changed = true;
        notifyAll();
    }

    public synchronized T get() {
        while (!changed) {
            try { wait(); } catch (Exception e) {}
        }
        changed = false;
        return value;
    }
}