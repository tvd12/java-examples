package com.tvd12.example.concurrent.lock;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LockKeyProvider {

	protected Object lock = new Object();
	protected Set<String> providedKeys = new HashSet<>();
	protected Map<String, Long> waitingKeys = new HashMap<>();
	
	public LockKeyProvider() {
	}
	
	public void provide(String key) {
		synchronized (lock) {
			if(!providedKeys.contains(key))
				throw new IllegalArgumentException("must tick key: " + key + " before");
			decreaseWaitingKey(key);
		}
	}
	
	public void provide(Set<String> keys) {
		synchronized (lock) {
			if(!providedKeys.containsAll(keys))
				throw new IllegalArgumentException("must tick keys: " + keys + " before");
			decreaseWaitingKeys(keys);
		}
	}
	
	public void retrieve(String key) {
		synchronized (lock) {
			providedKeys.remove(key);
		}
	}
	
	public void retrieve(Set<String> keys) {
		synchronized (lock) {
			providedKeys.removeAll(keys);
		}
	}
	
	public boolean isWaiting(String key) {
		synchronized (lock) {
			return isWaiting0(key);
		}
	}
	
	private boolean isWaiting0(String key) {
		Long count = waitingKeys.get(key);
		return count != null && count > 0;
	}
	
	public boolean isWaiting(Set<String> keys) {
		synchronized (lock) {
			return isWaiting0(keys);
		}
	}
	
	private boolean isWaiting0(Set<String> keys) {
		for(String key : keys) {
			Long count = waitingKeys.get(key);
			if(count != null && count > 0)
				return true;
		}
		return false;
	}
	
	public boolean tick(String key) {
		synchronized (lock) {
			return tick0(key);
		}
	}
	
	private boolean tick0(String key) {
		if(providedKeys.contains(key))
			return false;
		providedKeys.add(key);
		return true;
	}
	
	public boolean tick(Set<String> keys) {
		synchronized (lock) {
			return tick0(keys);
		}
	}
	
	private boolean tick0(Set<String> keys) {
		for(String key : keys) {
			if(providedKeys.contains(key)) {
				return false;
			}
		}
		providedKeys.addAll(keys);
		return true;
	}
	
	public boolean isProvable(String key) {
		synchronized (lock) {
			boolean provable = !isWaiting0(key) && tick0(key);
			if(!provable)
				increaseWaitingKey(key);
			return provable;
		}
	}
	
	public boolean isProvable(Set<String> keys) {
		synchronized (lock) {
			boolean provable = !isWaiting0(keys) && tick0(keys);
			if(!provable)
				increaseWaitingKeys(keys);
			return provable;
		}
	}
	
	private void increaseWaitingKey(String key) {
		waitingKeys.compute(key, (k, v) -> v != null ? v + 1 : 1);
	}
	
	private void decreaseWaitingKey(String key) {
		waitingKeys.computeIfPresent(key, (k, v) -> v - 1);
	}
	
	private void increaseWaitingKeys(Set<String> keys) {
		for(String key : keys)
			waitingKeys.compute(key, (k, v) -> v != null ? v + 1 : 1);
	}
	
	private void decreaseWaitingKeys(Set<String> keys) {
		for(String key : keys) {
			waitingKeys.computeIfPresent(key, (k, v) -> v - 1);
		}
		for(String key : keys) {
			Long amount = waitingKeys.get(key);
			if(amount != null && amount <= 0)
				waitingKeys.remove(key);
		}
	}
}
