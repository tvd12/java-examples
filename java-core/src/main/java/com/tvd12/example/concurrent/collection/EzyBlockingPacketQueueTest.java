package com.tvd12.example.concurrent.collection;

import com.tvd12.example.concurrent.lock.EzyBlockingPacketQueue;

public class EzyBlockingPacketQueueTest {
	
	public static void main(String[] args) throws InterruptedException {
		EzyBlockingPacketQueue queue = new EzyBlockingPacketQueue();
		Thread thread = new Thread(() -> {
			String str;
			try {
				while(true) {
					str = queue.peek();
					System.out.println("peek str = " + str + ", size = " + queue.size());
					str = queue.take();
					System.out.println("take str = " + str + ", size = " + queue.size());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		thread.start();
		Thread thread2 = new Thread(() -> {
			queue.add("hello");
			System.out.println("add 1 size = " + queue.size());
		});
		thread2.start();
		Thread.sleep(3);
		Thread thread3 = new Thread(() -> {
			System.out.println("add 2 start size = " + queue.size());
			queue.add("world");
			System.out.println("add 2 size = " + queue.size());
		});
		thread3.start();
		Thread.sleep(3000);
	}
	
}
