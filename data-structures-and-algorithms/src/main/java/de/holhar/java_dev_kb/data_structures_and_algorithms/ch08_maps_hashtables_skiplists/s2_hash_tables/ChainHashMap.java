package de.holhar.java_dev_kb.data_structures_and_algorithms.ch08_maps_hashtables_skiplists.s2_hash_tables;

import de.holhar.java_dev_kb.data_structures_and_algorithms.ch07_priority_queues.s1_prioty_queue.Entry;
import de.holhar.java_dev_kb.data_structures_and_algorithms.ch08_maps_hashtables_skiplists.s1_maps.UnsortedTableMap;
import java.util.ArrayList;
import java.util.List;

public class ChainHashMap<K,V> extends AbstractHashMap<K,V> {

  // a fixed capacity array of UnsortedTableMap that serve as buckets
  private UnsortedTableMap<K,V>[] table; // initialized within createTable

  public ChainHashMap() {
    super();
  }

  public ChainHashMap(int capacity) {
    super(capacity);
  }

  public ChainHashMap(int capacity, int prime) {
    super(capacity, prime);
    }

    /**
     * Creates an empty table having length equal to current capacity.
     */
  @Override
  protected void createTable() {
    table = (UnsortedTableMap<K, V>[]) new UnsortedTableMap[capacity];
  }

  /**
   * Returns value associated with key k in bucket with hash value h, or else null.
   */
  @Override
  protected V bucketGet(int h, K k) {
    UnsortedTableMap<K, V> bucket = table[h];
    if (bucket == null)
      return null;
    return bucket.get(k);
  }

  /**
   * Associates key k with value v in bucket with hash value h; returns old value.
   */
  @Override
  protected V bucketPut(int h, K k, V v) {
    UnsortedTableMap<K, V> bucket = table[h];
    if (bucket == null)
      bucket = new UnsortedTableMap<>();
    int oldSize = bucket.size();
    V answer = bucket.put(k, v);
    n += (bucket.size() - oldSize);
    return answer;
  }

  /**
   * Removes entry having key k from bucket with hash value h (if any).
   */
  @Override
  protected V bucketRemove(int h, K k) {
    UnsortedTableMap<K, V> bucket = table[h];
    if (bucket == null)
      return null;
    int oldSize = bucket.size();
    V answer = bucket.remove(k);
    n += (bucket.size() - oldSize);
    return answer;
  }

  /**
   * Returns an iterable collection of all key-value entries of the map.
   */
  @Override
  public Iterable<Entry<K, V>> entrySet() {
    List<Entry<K,V>> buffer = new ArrayList<>();
    for (int h = 0; h < capacity; h++) {
      if (table[h] != null) {
        for (Entry<K,V> entry : table[h].entrySet()) {
          buffer.add(entry);
        }
      }
    }
    return buffer;
  }
}
