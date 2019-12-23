package com.tvd12.example.concurrent.lock;

public class WaitTest2 {

	public synchronized void wakeup() {
		notify();
	}
	
	public static void main(String[] args) {
		WaitTest2 test = new WaitTest2();
		test.wakeup();
	}
	
}
