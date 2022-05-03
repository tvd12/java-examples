package com.tvd12.example.concurrent.lock;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;

public class LockProvider {

    protected LockFactory lockFactory;
    protected Object lock = new Object();
    protected Set<String> providedLockKeys = new HashSet<>();
    protected Map<String, Long> waitingLockKeys = new HashMap<>();

    public LockProvider(LockFactory lockFactory) {
        this.lockFactory = lockFactory;
    }

    public Lock provideLock(String key) {
        synchronized (lock) {
            if (!providedLockKeys.contains(key)) {
                throw new IllegalArgumentException("must tick key: " + key + " before");
            }
            Lock lock = lockFactory.getLock(key);
            providedLockKeys.add(key);
            decreaseWaitingLockKey(key);
            return lock;
        }
    }

    public Map<String, Lock> provideLocks(Set<String> keys) {
        synchronized (lock) {
            if (!providedLockKeys.containsAll(keys)) {
                throw new IllegalArgumentException("must tick keys: " + keys + " before");
            }
            Map<String, Lock> locks = lockFactory.getLocks(keys);
            providedLockKeys.addAll(keys);
            decreaseWaitingLockKeys(keys);
            return locks;
        }
    }

    public Lock getLock(String key) {
        synchronized (lock) {
            if (!providedLockKeys.contains(key)) {
                throw new IllegalArgumentException("must tick key: " + key + " before");
            }
            Lock lock = lockFactory.getLock(key);
            return lock;
        }
    }

    public Map<String, Lock> getLocks(Set<String> keys) {
        synchronized (lock) {
            if (!providedLockKeys.containsAll(keys)) {
                throw new IllegalArgumentException("must tick keys: " + keys + " before");
            }
            Map<String, Lock> locks = lockFactory.getLocks(keys);
            return locks;
        }
    }

    public void retrieveLock(String key) {
        synchronized (lock) {
            providedLockKeys.remove(key);
        }
    }

    public void retrieveLocks(Set<String> keys) {
        synchronized (lock) {
            providedLockKeys.removeAll(keys);
        }
    }

    public boolean isWaitingKey(String key) {
        synchronized (lock) {
            return isWaitingKey0(key);
        }
    }

    private boolean isWaitingKey0(String key) {
        Long count = waitingLockKeys.get(key);
        return count != null && count > 0;
    }

    public boolean isWaitingKeys(Set<String> keys) {
        synchronized (lock) {
            return isWaitingKeys0(keys);
        }
    }

    private boolean isWaitingKeys0(Set<String> keys) {
        for (String key : keys) {
            Long count = waitingLockKeys.get(key);
            if (count != null && count > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean tickFreeLock(String key) {
        synchronized (lock) {
            return tickFreeLock0(key);
        }
    }

    private boolean tickFreeLock0(String key) {
        if (providedLockKeys.contains(key)) {
            return false;
        }
        providedLockKeys.add(key);
        return true;
    }

    public boolean tickFreeLocks(Set<String> keys) {
        synchronized (lock) {
            return tickFreeLocks0(keys);
        }
    }

    private boolean tickFreeLocks0(Set<String> keys) {
        for (String key : keys) {
            if (providedLockKeys.contains(key)) {
                return false;
            }
        }
        providedLockKeys.addAll(keys);
        return true;
    }

    public boolean isProvableKey(String key) {
        synchronized (lock) {
            boolean provable = !isWaitingKey0(key) && tickFreeLock0(key);
            if (!provable) {
                increaseWaitingLockKey(key);
            }
            return provable;
        }
    }

    public boolean isProvableKeys(Set<String> keys) {
        synchronized (lock) {
            boolean provable = !isWaitingKeys0(keys) && tickFreeLocks0(keys);
            if (!provable) {
                increaseWaitingLockKeys(keys);
            }
            return provable;
        }
    }

    private void increaseWaitingLockKey(String key) {
        waitingLockKeys.compute(key, (k, v) -> v != null ? v + 1 : 1);
    }

    private void decreaseWaitingLockKey(String key) {
        waitingLockKeys.computeIfPresent(key, (k, v) -> v - 1);
    }

    private void increaseWaitingLockKeys(Set<String> keys) {
        for (String key : keys) {
            waitingLockKeys.compute(key, (k, v) -> v != null ? v + 1 : 1);
        }
    }

    private void decreaseWaitingLockKeys(Set<String> keys) {
        for (String key : keys) {
            waitingLockKeys.computeIfPresent(key, (k, v) -> v - 1);
        }
        for (String key : keys) {
            Long amount = waitingLockKeys.get(key);
            if (amount != null && amount <= 0) {
                waitingLockKeys.remove(key);
            }
        }
    }
}
