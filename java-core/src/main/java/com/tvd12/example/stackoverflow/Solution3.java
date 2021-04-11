package com.tvd12.example.stackoverflow;

import java.util.HashSet;
import java.util.Set;

public class Solution3 {

	public static int solution(int[] A) {
		int start = 0;
		int end = start + 1;
		Set<Integer> dest = new HashSet<>();
		for (int i = 0; i < A.length; i++) {
			if (A[start] == A[i] && i != 0) {
				start++;
				end = start + 1;
			} else {
				if (!dest.contains(A[i])) {
					end = i;
				}
			}
			dest.add(A[i]);
		}
		return end - start + 1;
	}

	public static void main(String[] args) {
		System.out.println(solution(new int[] {2, 1, 1, 3, 2, 1, 1, 3}));
	}

}
