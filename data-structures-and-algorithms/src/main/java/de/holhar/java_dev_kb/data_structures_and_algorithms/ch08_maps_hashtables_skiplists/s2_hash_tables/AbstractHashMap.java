package de.holhar.java_dev_kb.data_structures_and_algorithms.ch08_maps_hashtables_skiplists.s2_hash_tables;

import de.holhar.java_dev_kb.data_structures_and_algorithms.ch07_priority_queues.s1_prioty_queue.Entry;
import de.holhar.java_dev_kb.data_structures_and_algorithms.ch08_maps_hashtables_skiplists.s1_maps.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractHashMap<K,V> extends AbstractMap<K,V> {

  // number of entries in the dictionary
  protected int n = 0;

  // length of the table
  protected int capacity;

  // prime factor
  private final int prime;

  // the shift and scaling factors
  private final long scale;
  private final long shift;

  protected AbstractHashMap(int capacity, int prime) {
    this.prime = prime;
    this.capacity = capacity;
    Random random = new Random();
    this.scale = random.nextInt(prime - 1) + 1;
    this.shift = random.nextInt(prime);
    createTable();
  }

  protected AbstractHashMap(int capacity) {
    this(capacity, 109_345_121);
  }

  protected AbstractHashMap() {
    this(17);
  }

  // public methods
  public int size() {
    return n;
  }

  public V get(K key) {
    return bucketGet(hashValue(key), key);
  }

  public V remove(K key) {
    return bucketRemove(hashValue(key), key);
  }

  public V put(K key, V value) {
    V answer = bucketPut(hashValue(key), key, value);

    // keep load factor <= 0.5 (or find nearby prime)
    if (n >= capacity/2)
      resize(2 * capacity - 1);
    return answer;
  }

  // private utilities
  private int hashValue(K key) {
    return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
  }

  private void resize(int newCap) {
    List<Entry<K,V>> buffer = new ArrayList<>(n);
    for (Entry<K,V> e : entrySet()) {
      buffer.add(e);
    }
    capacity = newCap;
    createTable();                      // based on updated capacity
    n = 0;                              // will be recomputed while reinserting entries
    for (Entry<K,V> e : buffer) {
      put(e.getKey(), e.getValue());
    }
  }

  // protected abstract methods to be implemented by subclass
  protected abstract void createTable();
  protected abstract V bucketGet(int h, K k);
  protected abstract V bucketPut(int h, K k, V v);
  protected abstract V bucketRemove(int h, K k);
}
