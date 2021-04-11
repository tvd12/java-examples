package com.tvd12.example.stackoverflow;

class Clerk extends Thread {
    private Bank bank;
    private volatile boolean done;

    public Clerk(String name, Bank bank) {
        super(name);
        this.done = false;
        this.bank=bank;
        start();
    }

    public void run() {
        for (long i=0; i<1000; i++) {
            int accountNumberFrom = (int) (Math.random()*100);
            int accountNumberTo = (int) (Math.random()*100);
            float amount = (int) (Math.random()*1000) - 500;
            bank.transferMoney(accountNumberFrom, amount);
            bank.transferMoney(accountNumberTo, -amount);
        }
        this.done = true;
    }
    
    public boolean isDone() {
		return done;
	}
}

class Account {
	
	protected float balance;

	public float getBalance() {
		return balance;
	}

	public void setBalance(float newBalance) {
		this.balance = newBalance;
	}
	
}

class Bank {
    Account[] account;

    public Bank() {
        account = new Account[100];
        for (int i=0; i < account.length; i++)
            account[i] = new Account();
    }

    public synchronized void transferMoney(int accountNumber, float amount) {
        float oldBalance = account[accountNumber].getBalance();
        float newBalance = oldBalance + amount;
        account[accountNumber].setBalance(newBalance);
    }
}

public class Banking {
    public static void main (String[] args) throws Exception {
    	for(int j = 0 ; j < 1000 ; ++j) {
	        Bank myBank = new Bank();
	        /**
	         * balance before transactions
	         */
	        float sum=0;
	        for (int i=0; i<100; i++)
	            sum+=myBank.account[i].getBalance();
	        System.out.println("before: " + sum);
	
	        Clerk a = new Clerk ("Tom", myBank);
	        Clerk b = new Clerk ("Dick", myBank);
	        
	        while(!a.isDone() || !b.isDone()) // wait util all thread done
	        	Thread.sleep(1);
	
	        /**
	         * balance after transactions
	         */
	        for (int i=0; i<100; i++)
	            sum+=myBank.account[i].getBalance();
	
	        System.out.println("after: " + sum);
    	}
    }
}
