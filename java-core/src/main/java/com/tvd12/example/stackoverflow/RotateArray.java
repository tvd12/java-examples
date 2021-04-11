package com.tvd12.example.stackoverflow;

public class RotateArray {

	public int[] solution(int[] A, int K) {
		if(A.length == 0)
			return A;
		for(int k = 0 ; k < K ; ++k) {
			int tmp = A[A.length - 1];
			for(int i = A.length - 1 ; i > 0 ; --i)
				A[i] = A[i - 1];
			A[0] = tmp;
		}
		return A;
    }
	
}
