package com.tvd12.example.reactive.core;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

public final class Reactive {
    private static final ExecutorService EXECUTOR_SERVICE =
        Executors.newFixedThreadPool(32);

    private Reactive() {
    }

    public static Single single() {
        return new Single();
    }

    public static void destroy() {
        EXECUTOR_SERVICE.shutdown();
    }

    public static class Single {
        private final Map<String, RxSupplier> suppliers = new HashMap<>();

        public Single register(String name, RxSupplier supplier) {
            suppliers.put(name, supplier);
            return this;
        }

        public <T> T blockingGet(Function<Map<String, Object>, T> mapper) {
            final Map<String, Object> result = new ConcurrentHashMap<>(suppliers.size());
            final List<Exception> exceptions = new ArrayList<>();
            final CountDownLatch countDown = new CountDownLatch(suppliers.size());
            for (Map.Entry<String, RxSupplier> entry : suppliers.entrySet()) {
                final String name = entry.getKey();
                EXECUTOR_SERVICE.execute(() -> {
                    try {
                        final Object value = entry.getValue().get();
                        result.put(name, value);
                        countDown.countDown();
                    }
                    catch (Exception e) {
                        exceptions.add(e);
                    }
                });
            }
            try {
                countDown.await();
            }
            catch (Exception e) {
                exceptions.add(e);
            }
            if (exceptions.isEmpty()) {
                return mapper.apply(result);
            }
            else {
                throw new RxException(exceptions);
            }
        }
    }

    public static interface RxSupplier {
        Object get() throws Exception;
    }

    @Getter
    public static class RxException extends RuntimeException {
        private final List<Exception> exceptions;

        public RxException(List<Exception> exceptions) {
            super(exceptions.toString());
            this.exceptions = exceptions;
        }
    }
}
