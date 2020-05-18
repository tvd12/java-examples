package com.tvd12.example.reflection;

import lombok.Getter;
import lombok.Setter;

public class ReflectionExample {
	
	public static void main(String[] args) throws Exception {
		System.out.println(Employee.class.getMethod("setId", int.class));
	}
	
	@Getter
	@Setter
	public static class Employee {

		@ExampleId
		private int id;
		private String firstName, lastName;
		
	}
	
}
