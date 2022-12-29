package de.holhar.java_dev_kb.data_structures_and_algorithms.ch08_maps_hashtables_skiplists.s3_sorted_maps;

import de.holhar.java_dev_kb.data_structures_and_algorithms.ch07_priority_queues.s1_prioty_queue.DefaultComparator;
import de.holhar.java_dev_kb.data_structures_and_algorithms.ch08_maps_hashtables_skiplists.s1_maps.AbstractMap;
import java.util.Comparator;

public abstract class AbstractSortedMap<K,V> extends AbstractMap<K,V> {

  private Comparator<K> comp;

  protected AbstractSortedMap(Comparator<K> c) {
    this.comp = c;
  }

  protected AbstractSortedMap() {
    this(new DefaultComparator<>());
  }

  protected int compare(K key, MapEntry<K,V> entry) {
    return comp.compare(entry.getKey(), key);
  }

  /**
   * Returns the entry with smallest key value (or null, if the map is empty).
   */
  public abstract MapEntry<K,V> firstEntry();

  /**
   * Returns the entry with largest key value (or null, if the map is empty).
   */
  public abstract MapEntry<K,V> lastEntry();

  /**
   * Returns the entry with the least key value greater than or equal to k (or null, if no such
   * entry exists).
   */
  public abstract MapEntry<K,V> ceilingEntry(K key);

  /**
   * Returns the entry with the greatest key value less than or equal to k (or null, if no such
   * entry exists).
   */
  public abstract MapEntry<K,V> floorEntry(K key);

  /**
   * Returns the entry with the greatest key value strictly less than k (or null, if no such
   * entry exists).
   */
  public abstract MapEntry<K,V> lowerEntry(K key);

  /**
   * Returns the entry with the least key value strictly greater than k (or null if no such entry
   * exists).
   */
  public abstract MapEntry<K,V> higherEntry(K key);

  /**
   * Returns an iteration of all entries with key greater than or equal to k1 , but strictly less
   * than k2 .
   */
  public abstract Iterable<MapEntry<K,V>> subMap(K fromKey, K toKey);
}
