package com.tvd12.example;

public class FinalizeTest {

	public static void main(String[] args) throws Exception {
		A a = new A();
		a.g();
		a = null;
	    System.gc(); 
	    System.out.println("done");
	}
	
	public static class A {
		
		public void g() {
			
		}
		
		@Override
		protected void finalize() throws Throwable {
			System.out.println("i called by gc");
		}
	}
}
