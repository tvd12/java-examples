package com.tvd12.javaexample.exceptionhandler;

public class ExceptionHandlerExample {
	public static void main(String[] args) throws Exception {

		Handler handler = new Handler();
		Thread.setDefaultUncaughtExceptionHandler(handler);

		Thread t = new Thread(new SomeThread(), "Some Thread");
		t.start();

		Thread.sleep(100);

		throw new RuntimeException("Thrown from Main");
	}

}

class Handler implements Thread.UncaughtExceptionHandler {
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("Throwable: " + e.getMessage());
		System.out.println(t.toString());
	}
}

class SomeThread implements Runnable {
	public void run() {
		throw new RuntimeException("Thrown From Thread");
	}
}