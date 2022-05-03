package com.tvd12.example.test;

import com.tvd12.test.performance.Performance;

import java.util.HashMap;
import java.util.Map;

public class FunctionalPerformanceTest {

    public static void main(String[] args) {
        long oldWayTime = Performance.create()
            .test(() -> {
                Map<String, String> map = new HashMap<>();
                String value = map.get("value");
                if (value == null) {
                    map.put("value", "value");
                }
            })
            .getTime();

        long functionalTime = Performance.create()
            .test(() -> {
                Map<String, String> map = new HashMap<>();
                map.computeIfAbsent("value", k -> {
                    return "value";
                });
            })
            .getTime();
        System.out.println("old way time: " + oldWayTime);
        System.out.println("functional time: " + functionalTime);
    }

}
