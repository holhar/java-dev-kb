package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec04_concurrent_collections;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryConsistencyErrors {

    public static void main(String[] args) {

        Map<String, Object> foodData1 = new HashMap<>();
        foodData1.put("Penguin", 1);
        foodData1.put("Flamingo", 2);
        for (String key : foodData1.keySet()) {
            // foodData1.remove(key); // Throws ConcurrentModificationException
        }

        Map<String, Object> foodData2 = new ConcurrentHashMap<>();
        foodData2.put("Penguin", 1);
        foodData2.put("Flamingo", 2);
        for (String key : foodData2.keySet()) {
            foodData2.remove(key); // DOES NOT throw ConcurrentModificationException
        }
    }
}
