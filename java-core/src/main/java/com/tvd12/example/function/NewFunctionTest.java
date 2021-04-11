package com.tvd12.example.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class NewFunctionTest {

	public static void main(String[] args) {
		int count = 100000;
		List<A> list = new ArrayList<>();
		for(int i = 0 ; i < count ; i++) {
			int key = ThreadLocalRandom.current().nextInt(100000);
			int value = ThreadLocalRandom.current().nextInt(1000000);
			list.add(new A(key, value));
		}
		System.out.println("init list done");
		Map<Integer, List<Integer>> map = new HashMap<>();
		for(A a : list) {
			map.computeIfAbsent(a.getKey(), k -> new ArrayList<>());
		}
		System.out.println("finish function");
	}
	
	@Getter
	@AllArgsConstructor
	public static class A {
		
		private final int key;
		private final int value;
		
	}
	
}
