package com.tvd12.example.concurrent.lock;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;

import com.tvd12.ezyfoxserver.util.EzyEquals;
import com.tvd12.ezyfoxserver.util.EzyHashCodes;

import lombok.Getter;

public class Locking {

	@Getter
	protected long id;
	@Getter
	protected Set<String> keys;
	protected LockKeyProvider lockKeyProvider;
	protected LockingQueue lockingQueue;
	protected LockingExecutor lockingExecutor;
	protected volatile boolean acquired;
	
	public static final AtomicLong ID_GENTOR = new AtomicLong(0L);
	
	public Locking(
			LockKeyProvider lockKeyProvider, 
			LockingQueue lockingQueue, 
			LockingExecutor lockingExecutor, Set<String> keys) {
		this.lockKeyProvider = lockKeyProvider;
		this.lockingQueue = lockingQueue;
		this.lockingExecutor = lockingExecutor;
		this.keys = keys;
		this.id  = ID_GENTOR.incrementAndGet();
	}
	
	public synchronized void lock() {
		acquired = !lockingQueue.add(this, lockKeyProvider);
		while(!acquired)
			wait0();
		lockKeyProvider.provide(keys);
	}
	
	public void unlock() {
		lockingQueue.check(lockKeyProvider, keys);
	}
	
	public synchronized void active() {
		setAcquired();
		notify();
	}
	
	private void wait0() {
		try {
			wait();
		} catch (InterruptedException e) {
		}
	}
	
	protected void setAcquired() {
		acquired = true;
	}
	
	protected void lock(Map<String, Lock> locks) {
		lockingExecutor.lock(locks);
	}
	
	protected void unlock(Map<String, Lock> locks) {
		lockingExecutor.unlock(locks);
	}
	
	@Override
	public boolean equals(Object obj) {
		return new EzyEquals<Locking>()
				.function(t -> t.id)
				.isEquals(this, obj);
	}
	
	@Override
	public int hashCode() {
		return new EzyHashCodes().append(id).toHashCode();
	}

}
