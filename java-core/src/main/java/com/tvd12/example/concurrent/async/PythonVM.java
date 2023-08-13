package com.tvd12.example.concurrent.async;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PythonVM {

    public static void main(String[] args) {
        connectToDatabase();
        getDataById(1);
    }

    public static Promise<Void> connectToDatabase() {
        return new Promise<Void>(() ->
            DatabaseClient.getInstance().connect()
        )
            .then(() ->
                System.out.println("database connect successfully")
            );
    }

    public static Promise<String> getDataById(long id) {
        return new Promise<String>(() -> {
            DatabaseClient
                .getInstance()
                .getDataById(1);
        })
            .then(it -> System.out.println("data is: " + it));
    }

    public static class Promise<T> {
        private Consumer<T> then;
        private AtomicReference<T> resultRef;

        public Promise(Runnable task) {
            this(() -> {
                task.run();
                return null;
            });
        }

        public Promise(Supplier<T> task) {
            MainThread
                .getInstance()
                .addToQueue(() -> {
                    T result = task.get();
                    resultRef = new AtomicReference<>(result);
                    if (then != null) {
                        then.accept(result);
                    }
                });
        }

        public Promise<T> then(Runnable callback) {
            return then(it -> {
                callback.run();
            });
        }

        public Promise<T> then(Consumer<T> callback) {
            MainThread.getInstance()
                .addToQueue(() -> {
                    if (resultRef != null) {
                        callback.accept(resultRef.get());
                    } else {
                        this.then = callback;
                    }
                });
            return this;
        };
    }

    public static class DatabaseClient {

        private Thread thread;
        private final AtomicBoolean connected = new AtomicBoolean();
        private final BlockingQueue<Data> dataQueue =
            new LinkedBlockingQueue<>();

        private static final DatabaseClient INSTANCE = new DatabaseClient();

        private DatabaseClient() {}

        public static DatabaseClient getInstance() {
            return INSTANCE;
        }

        public void connect() {
            thread = new Thread(() -> {
                connected.set(true);
                while (true) {
                    Thread.currentThread().setName("database-client");
                    try {
                        Data data = dataQueue.take();
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            });
            thread.start();
        }

        public String getDataById(long id) {
            if (!connected.get()) {
                throw new IllegalStateException("database not connected");
            }
            return "data" + id;
        }
    }

    @Getter
    @AllArgsConstructor
    private static class Data {
        private long id;
        private String value;
    }

    public static class MainThread {

        private final Queue<Runnable> queue = new LinkedList<>();
        public static final MainThread INSTANCE = new MainThread();

        private MainThread() {
            Thread newThread = new Thread(() -> {
                List<Runnable> buffer = new ArrayList<>();
                while (true) {
                    Thread.currentThread().setName("main-thread");
                    synchronized (queue) {
                        buffer.addAll(queue);
                        queue.clear();
                    }
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        break;
                    }
                    try {
                        buffer.forEach(task -> {
                            try {
                                task.run();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    } finally {
                        buffer.clear();
                    }
                }
            });
            newThread.start();
        }

        public static MainThread getInstance() {
            return INSTANCE;
        }

        public void addToQueue(Runnable task) {
            synchronized (queue) {
                queue.add(task);
            }
        }
    }
}
