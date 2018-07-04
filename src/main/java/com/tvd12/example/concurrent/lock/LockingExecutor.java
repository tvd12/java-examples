package com.tvd12.example.concurrent.lock;

import java.util.Map;
import java.util.concurrent.locks.Lock;

public class LockingExecutor {

	public void lock(Map<String, Lock> locks) {
		for(String key : locks.keySet()) {
			Lock lock = locks.get(key);
			lock.lock();
		}
	}
	
	public void unlock(Map<String, Lock> locks) {
		for(String key : locks.keySet()) {
			Lock lock = locks.get(key);
			lock.unlock();
		}
	}
	
}
