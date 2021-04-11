package com.tvd12.example.stackoverflow;

public class Solution {

	public static int solution(int N, int K) {
		int result = 0;
		int remainChip = N;
		int remainAllIn = K;
		while (remainChip > 3 && remainAllIn > 0) {
			if (remainChip % 2 != 0) {
				remainChip -= 1;
			} 
			else {
				remainChip /= 2;
				remainAllIn -= 1;
			}
			result += 1;
		}
		return result + N - 1;
	}

	public static void main(String[] args) {
		System.out.println(solution(10, 10));
	}

}
