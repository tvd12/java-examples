package com.tvd12.example.concurrent.lock;

import java.util.Set;

public class LockingContext {

	protected LockKeyProvider lockKeyProvider;
	protected LockingQueue lockingQueue;
	protected LockingExecutor lockingExecutor;
	
	public LockingContext() {
		this.lockingQueue = new LockingQueue();
		this.lockKeyProvider = new LockKeyProvider();
		this.lockingExecutor = new LockingExecutor();
	}
	
	public Locking newLock(Set<String> keys) {
		return new Locking(lockKeyProvider, lockingQueue, lockingExecutor, keys);
	}
	
}
