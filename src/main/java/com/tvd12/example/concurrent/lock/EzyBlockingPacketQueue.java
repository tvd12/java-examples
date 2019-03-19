package com.tvd12.example.concurrent.lock;

import java.util.LinkedList;
import java.util.Queue;

public class EzyBlockingPacketQueue {

	private final int capacity;
	private final Queue<String> queue ;
	private volatile boolean empty = true;
	private volatile boolean processing = false;
	
	public EzyBlockingPacketQueue() {
		this(10000);
	}
	
	public EzyBlockingPacketQueue(int capacity) {
		this.capacity = capacity;
		this.queue = new LinkedList<>();
	}
	
	public int size() {
		synchronized (queue) {
			int size = queue.size();
			return size;
		}
	}
	
	public void clear() {
		synchronized (queue) {
			empty = true;
			queue.clear();
		}
	}
	
	public synchronized String take() {
		synchronized (queue) {
			String packet = queue.poll();
			processing = false;
			empty = queue.isEmpty();
			notifyAll();
		    return packet;
		}
	}
	
	public synchronized String peek() throws InterruptedException {
		while(empty || processing)
			wait();
		synchronized (queue) {
			processing = true;
			String packet = queue.peek();
		    return packet;
		}
		
	}
	
	public boolean isFull() {
		synchronized (queue) {
			int size = queue.size();
			boolean full = size >= capacity;
			return full;
		}
	}
	
	public boolean isEmpty() {
		synchronized (queue) {
			boolean empty = queue.isEmpty();
			return empty;
		}
	}
	
	public synchronized boolean add(String packet) {
		synchronized (queue) {
			int size = queue.size();
			if(size >= capacity) 
		        return false;
		    boolean success = queue.offer(packet);
		    if(success) {
		    		empty = false;
		    		if(!processing)
		    			notifyAll();
		    }
		    return success;
		}
	}
	
	public void wakeup() {
		this.processing = false;
	}
	
}
