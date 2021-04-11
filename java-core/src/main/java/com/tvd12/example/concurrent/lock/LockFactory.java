package com.tvd12.example.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockFactory {

	protected ConcurrentHashMap<String, Lock> locks = new ConcurrentHashMap<>();
	
	public Lock getLock(String key) {
		return locks.computeIfAbsent(key, k -> newLock());
	}
	
	public Map<String, Lock> getLocks(Set<String> keys) {
		Map<String, Lock> map = new HashMap<>();
		for(String key : keys)
			map.put(key, getLock(key));
		return map;
	}
	
	protected Lock newLock() {
		return new ReentrantLock();
	}
	
}
