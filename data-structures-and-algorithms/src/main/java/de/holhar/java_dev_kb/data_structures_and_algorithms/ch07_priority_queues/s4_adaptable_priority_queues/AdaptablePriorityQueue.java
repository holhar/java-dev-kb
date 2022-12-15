package de.holhar.java_dev_kb.data_structures_and_algorithms.ch07_priority_queues.s4_adaptable_priority_queues;

import de.holhar.java_dev_kb.data_structures_and_algorithms.ch07_priority_queues.s1_prioty_queue.Entry;

public interface AdaptablePriorityQueue<K,V> {

  void remove(Entry<K,V> entry);
  void replaceKey(Entry<K,V> entry, K key);
  void replaceValue(Entry<K,V> entry, V value);
}
