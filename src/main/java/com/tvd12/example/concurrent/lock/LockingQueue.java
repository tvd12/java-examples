package com.tvd12.example.concurrent.lock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LockingQueue {

	protected Queue<Locking> queue = new LinkedList<>();
	
	public boolean add(Locking lock, LockKeyProvider lockKeyProvider) {
		synchronized (queue) {
			Set<String> keys = lock.getKeys();
			if(lockKeyProvider.isProvable(keys))
				return false;
			queue.add(lock);
			return true;
		}
	}
	
	public void check(LockKeyProvider lockKeyProvider, Set<String> retrievableKeys) {
		synchronized (queue) {
			lockKeyProvider.retrieve(retrievableKeys);
			if(queue.isEmpty())
				return;
			int size = queue.size();
			for(int i = 0 ; i < size ; i++) {
				Locking lock = queue.poll();
				if(lockKeyProvider.tick(lock.getKeys())) {
					lock.active();
					return;
				}
				queue.add(lock);
			}
		}
		throw new RuntimeException("can't poll lock, system error");
	}
	
}
