package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec04_concurrent_collections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ZooManager {

    // Thread safe usage of a map
    private Map<String, Object> foodData = new ConcurrentHashMap<>();

    public void put(String key, String value) {
        foodData.put(key, value);
    }

    public Object get(String key) {
        return foodData.get(key);
    }
}
