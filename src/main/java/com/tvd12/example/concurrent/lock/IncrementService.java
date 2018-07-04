package com.tvd12.example.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IncrementService {

	private LockingContext lockingContext;
	private Map<String, Long> map = new HashMap<>();
	
	public IncrementService(LockingContext lockingContext) {
		this.lockingContext = lockingContext;
	}
	
	public Map<String, Long> incrementAndGet(Set<String> keys) {
		Locking locking = lockingContext.newLock(keys);
		locking.lock();
		try {
			for(String key : keys) {
				map.compute(key, (k, v) -> v != null ? v + 1L: 1L);
			}
			return map;
		}
		finally {
			locking.unlock();
		}
	}
	
}
