package com.tvd12.example.stackoverflow;

public class MaxBinGaps {

	public int solution(int N) {
		int max = 0;
		int count = 0;
		String str = Long.toBinaryString(N);
		for(int i = 0 ; i < str.length() ; ++i) {
			char ch = str.charAt(i);
			if(ch == '0') {
				++ count;
			}
			else {
				if(count > max)
					max = count;
				count = 0;
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		System.out.println(Long.toBinaryString(1041));
	}
}
