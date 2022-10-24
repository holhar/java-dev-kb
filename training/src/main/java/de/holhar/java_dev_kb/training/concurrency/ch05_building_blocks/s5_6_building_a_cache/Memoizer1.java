package de.holhar.java_dev_kb.training.concurrency.ch05_building_blocks.s5_6_building_a_cache;

import de.holhar.java_dev_kb.training.concurrency.utils.GuardedBy;

import java.util.HashMap;
import java.util.Map;

/**
 * Initial cache attempt using HashMap and synchronization.
 */
public class Memoizer1<A, V> implements Computable<A, V> {

    @GuardedBy("this")
    private final Map<A, V> cache = new HashMap<>();
    private final Computable<A, V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
