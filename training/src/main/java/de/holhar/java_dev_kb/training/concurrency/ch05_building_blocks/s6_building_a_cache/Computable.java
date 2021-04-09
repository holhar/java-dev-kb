package de.holhar.java_dev_kb.training.concurrency.ch05_building_blocks.s6_building_a_cache;

public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
