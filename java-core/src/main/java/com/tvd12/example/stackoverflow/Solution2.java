package com.tvd12.example.stackoverflow;

import java.util.HashMap;
import java.util.Map;

public class Solution2 {

    public static int solution(int[] A) {
        Map<Integer, Integer> neededVisit = new HashMap<>();
        for (int i = 0; i < A.length; ++i) {
            neededVisit.put(A[i], 1);
        }
        int missing = neededVisit.size();
        int currentIndex = 0;
        int resultStart = 0;
        int resultEnd = 0;
        for (int i = 1; i <= A.length; ++i) {
            int num = A[i - 1];
            System.out.println("needed[" + num + "]: " + neededVisit.get(num));
            if (neededVisit.get(num) > 0) {
                missing -= 1;
            }
            neededVisit.compute(num, (k, v) -> v - 1);
            System.out.println("missing: " + missing);
            if (missing <= 0) {
                while (currentIndex < i && neededVisit.get(A[currentIndex]) < 0) {
                    neededVisit.compute(A[currentIndex], (k, v) -> v + 1);
                    currentIndex += 1;
                }
                if (resultEnd <= 0 || i - currentIndex <= resultEnd - resultStart) {
                    resultStart = currentIndex;
                    resultEnd = i;
                }
            }
        }
        return resultEnd - resultStart;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 1, 1, 3, 2, 1, 1, 3}));
    }

}
