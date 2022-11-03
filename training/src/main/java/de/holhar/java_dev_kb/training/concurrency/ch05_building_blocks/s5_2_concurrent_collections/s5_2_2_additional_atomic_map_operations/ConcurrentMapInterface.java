package de.holhar.java_dev_kb.training.concurrency.ch05_building_blocks.s5_2_concurrent_collections.s5_2_2_additional_atomic_map_operations;

import java.util.Map;

/**
 * ConcurrentMap interface (showing it here for illustration).
 */
public interface ConcurrentMapInterface<K, V> extends Map<K, V> {

  // Insert into map only if no value is mapped from K
  V putIfAbsent(K key, V value);

  // Remove only if K is mapped to V
  //default boolean remove(K key, V value)

  // Replace value only if K is mapped to oldValue
  boolean replace(K key, V oldValue, V newValue);

  // Replace value only if K is mapped to some value
  V replace(K key, V newValue);
}
