package com.tvd12.example.concurrent.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyThreadPoolWithThreadArray {

	private Thread[] threads = new Thread[3];
	private List<String> queue = Collections.synchronizedList(new ArrayList<>());
	
	public MyThreadPoolWithThreadArray() {
		for(int i = 0 ; i < threads.length ; i++) {
			threads[i] = new Thread(this::runTasks);
			threads[i].setName("mythread-" + i);
		}
	}
	
	public void start() {
		for(int i = 0 ; i < threads.length ; i++) {
			threads[i].start();
		}
	}
	
	private void runTasks() {
		System.out.println(Thread.currentThread() + " start");
		while(true) {
			remove();
		}
	}
	
	private synchronized void remove() {
		try {
			while(queue.isEmpty())
				wait();
			String str = queue.remove(0);
			System.out.println(Thread.currentThread().getName() + 
					" <> " + str + ", queue.size = " + queue.size());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void execute(String str) {
			boolean empty = queue.isEmpty();
			queue.add(str);
			if(!empty)
				notifyAll();
	}
	
	public static void main(String[] args) {
		MyThreadPoolWithThreadArray pool = new MyThreadPoolWithThreadArray();
		pool.start();
		for(int i = 0 ; i < 1000 ; i++)
			pool.execute("hello-" + i);
		System.out.println("end main function");
	}
}
