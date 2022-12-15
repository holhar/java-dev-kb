package de.holhar.java_dev_kb.data_structures_and_algorithms.ch07_priority_queues.s1_prioty_queue;

/**
 * Interface for a key-value pair.
 */
public interface Entry<K, V> {
  K getKey();
  V getValue();
}
