package de.holhar.java_dev_kb.training.concurrency.ch05_building_blocks.s5_6_building_a_cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Replacing HashMap with ConcurrentHashMap.
 */
public class Memoizer2<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
