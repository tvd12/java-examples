package com.tvd12.example.concurrent.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MyThreadPoolThreadQueue {

	private Object lock = new Object();
	private Thread thread;
	private Queue<Thread> threads = new LinkedList<>();
	private List<String> queue = Collections.synchronizedList(new ArrayList<>());
	
	public MyThreadPoolThreadQueue() {
		thread = new Thread(this::runTasks);
		thread.setName("booss");
		for(int i = 0 ; i < 3 ; i++) {
			Thread t = new Thread(this::remove);
			t.setName("mythread-" + i);
			threads.add(t);
		}
		thread.start();
	}
	
	private synchronized void runTasks() {
		System.out.println(Thread.currentThread() + " start");
		while(true) {
			try {
				synchronized (lock) {
					while(queue.isEmpty())
						wait();
				}
				Thread t = threads.poll();
				if(t != null) {
					t.run();
					threads.add(t);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void remove() {
		try {
			synchronized (queue) {
				String str = queue.remove(0);
				System.out.println(Thread.currentThread().getName() + " <> " + str);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void execute(String str) {
		synchronized (queue) {
			queue.add(str);
			notify();
		}
	}
	
	public static void main(String[] args) {
		MyThreadPoolThreadQueue pool = new MyThreadPoolThreadQueue();
		for(int i = 0 ; i < 10000 ; i ++)
			pool.execute("hello - " + i);
	}
}
