package com.tvd12.example.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MapFilterNullValuesExample {
    public static void main(String[] args) {
        // Sample map with null values
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", null);
        map.put("key3", "value3");
        map.put("key4", null);

        // Filter out null values
        map.values().removeIf(Objects::isNull);

        // Print filtered map
        System.out.println("Filtered Map: " + map);
    }
}
