package de.holhar.java_dev_kb.training.concurrency.ch11_performance_and_scalability.s11_4_reducing_lock_contention.s11_4_3_lock_striping;

import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;

/**
 * Hash-based map using lock striping.
 */
@ThreadSafe
public class StripedMap {

  // Synchronization policy: buckets[n] guarded by locks[n%N_LOCKS]
  private static final int N_LOCKS = 16;
  private final Node[] buckets;
  private final Object[] locks;

  private static class Node {
    Object key;
    Object value;
    Node next;
    // ...
  }

  public StripedMap(int numBuckets) {
    buckets = new Node[numBuckets];
    locks = new Object[N_LOCKS];
    for (int i = 0; i < N_LOCKS; i++) {
      locks[i] = new Object();
    }
  }

  private int hash(Object key) {
    return Math.abs(key.hashCode() % buckets.length);
  }

  public Object get(Object key) {
    int hash = hash(key);
    synchronized (locks[hash % N_LOCKS]) {
      for (Node m = buckets[hash]; m != null; m = m.next) {
        if (m.key.equals(key)) {
          return m.value;
        }
      }
    }
    return null;
  }

  public void clear() {
    for (int i = 0; i < buckets.length; i++) {
      // It's not necessary to acquire all locks simultaneously
      synchronized (locks[i % N_LOCKS]) {
        buckets[i] = null;
      }
    }
  }

  // ...
}
