package com.tvd12.example.reactive.core;

import lombok.Getter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

public final class Reactive {
    private static final ExecutorService EXECUTOR_SERVICE =
        Executors.newFixedThreadPool(32);

    private Reactive() {}

    public static Single single() {
        return new Single();
    }

    public static void destroy() {
        EXECUTOR_SERVICE.shutdown();
    }

    public interface RxSupplier {
        Object get() throws Exception;
    }

    public static class Single {
        private final Map<Object, RxSupplier> suppliers = new HashMap<>();

        public Single register(Object name, RxSupplier supplier) {
            suppliers.put(name, supplier);
            return this;
        }

        public <T> T blockingGet(Function<RxMap, T> mapper) {
            final RxMap result = new RxMap();
            final List<Exception> exceptions = Collections.synchronizedList(
                new ArrayList<>()
            );
            final CountDownLatch countDown = new CountDownLatch(suppliers.size());
            for (Map.Entry<Object, RxSupplier> entry : suppliers.entrySet()) {
                final Object name = entry.getKey();
                EXECUTOR_SERVICE.execute(() -> {
                    try {
                        final Object value = entry.getValue().get();
                        result.put(name, value);
                        countDown.countDown();
                    } catch (Exception e) {
                        exceptions.add(e);
                    }
                });
            }
            try {
                countDown.await();
            } catch (Exception e) {
                exceptions.add(e);
            }
            if (exceptions.isEmpty()) {
                return mapper.apply(result);
            } else {
                throw new RxException(exceptions);
            }
        }
    }

    public static class RxMap {
        private final Map<Object, Object> map = new ConcurrentHashMap<>();

        public void put(Object name, Object value) {
            this.map.put(name, value);
        }

        @SuppressWarnings("unchecked")
        public <T> T get(Object name) {
            return (T) map.get(name);
        }
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
