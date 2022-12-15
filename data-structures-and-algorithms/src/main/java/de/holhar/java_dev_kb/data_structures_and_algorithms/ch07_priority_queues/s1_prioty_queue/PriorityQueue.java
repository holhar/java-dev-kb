package de.holhar.java_dev_kb.data_structures_and_algorithms.ch07_priority_queues.s1_prioty_queue;

/**
 * Interface for the priority queue ADT.
 */
public interface PriorityQueue<K,V> {

  /**
   * Returns the number of entries in the priority queue.
   */
  int size();

  /**
   * Returns a boolean indicating whether the priority queue is empty.
   */
  boolean isEmpty();

  /**
   * Creates an entry with key k and value v in the priority queue.
   */
  Entry<K,V> insert(K key, V value) throws IllegalArgumentException;

  /**
   * Returns (but does not remove) a priority queue entry (k,v) having minimal key; returns null
   * if the priority queue is empty.
   */
  Entry<K,V> min();

  /**
   * Removes and returns an entry (k,v) having minimal key from the priority queue, returns null
   * if the priority queue is empty.
   */
  Entry<K,V> removeMin();
}
