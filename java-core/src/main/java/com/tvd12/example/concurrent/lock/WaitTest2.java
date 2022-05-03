package com.tvd12.example.concurrent.lock;

public class WaitTest2 {

    public static void main(String[] args) {
        WaitTest2 test = new WaitTest2();
        test.wakeup();
    }

    public synchronized void wakeup() {
        notify();
    }

}
