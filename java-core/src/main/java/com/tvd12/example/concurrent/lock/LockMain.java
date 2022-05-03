package com.tvd12.example.concurrent.lock;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class LockMain {

    public static void main(String[] args) throws Exception {
        LockingContext context = new LockingContext();
        IncrementService service = new IncrementService(context);
        Set<String> keys = randomSet(5);
        Map<String, Long> map = service.incrementAndGet(keys);
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                service.incrementAndGet(keys);
            });
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        Thread.sleep(3000);
        System.out.println(map);
    }

    private static Set<String> randomSet(int size) {
        Random random = ThreadLocalRandom.current();
        Set<String> set = new HashSet<>();
        int randomSize = random.nextInt(size);
        for (int i = 0; i <= randomSize; i++) {
            set.add(String.valueOf(i));
        }
        return set;
    }

}
