package com.tvd12.example.concurrent.lock;

public class WaitTest {

    public static void main(String[] args) throws InterruptedException {
        Message msg = new Message("process it");
        Waiter waiter = new Waiter(msg);
        new Thread(waiter, "waiter").start();

//        Waiter waiter1 = new Waiter(msg);
//        new Thread(waiter1, "waiter1").start();
//        
//        Notifier notifier = new Notifier(msg);
//        new Thread(notifier, "notifier").start();
//        System.out.println("All the threads are started");
        Thread.sleep(3000);
    }

    public static class Message {
        private String msg;

        public Message(String str) {
            this.msg = str;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String str) {
            this.msg = str;
        }
    }

    public static class Waiter implements Runnable {
        private Message msg;

        public Waiter(Message m) {
            this.msg = m;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            synchronized (msg) {
                try {
                    System.out.println(name + " waiting to get notified at time:" + System.currentTimeMillis());
                    while (msg != null) {
                        try {
                            msg.wait(3000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("after wait");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(name + " waiter thread got notified at time:" + System.currentTimeMillis());
                // process the message now
                System.out.println(name + " processed: " + msg.getMsg());
            }
        }
    }

    public static class Notifier implements Runnable {
        private Message msg;

        public Notifier(Message msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name + " started");
            try {
                Thread.sleep(1000);
                synchronized (msg) {
                    msg.setMsg(name + " Notifier work done");
                    msg.notify();
                    // msg.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
