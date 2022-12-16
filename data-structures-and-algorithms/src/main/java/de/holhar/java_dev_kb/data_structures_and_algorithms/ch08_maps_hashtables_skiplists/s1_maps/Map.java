package de.holhar.java_dev_kb.data_structures_and_algorithms.ch08_maps_hashtables_skiplists.s1_maps;

import de.holhar.java_dev_kb.data_structures_and_algorithms.ch07_priority_queues.s1_prioty_queue.Entry;

public interface Map<K,V> {
  int size();
  boolean isEmpty();
  V get(K key);
  V put(K key, V value);
  V remove(K key);
  Iterable<K> keySet();
  Iterable<V> values();
  Iterable<Entry<K,V>> entrySet();
}
