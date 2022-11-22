package de.holhar.java_dev_kb.training.concurrency.ch14_custom_synchronizers.s14_1_managin_state_dependence.s14_1_3_condition_queues;

import de.holhar.java_dev_kb.training.concurrency.ch14_custom_synchronizers.s14_1_managin_state_dependence.BaseBoundedBuffer;
import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;

/**
 * Bounded buffer using condition queues.
 */
@ThreadSafe
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {

  // CONDITION PREDICATE: not-full (!isFull())
  // CONDITION PREDICATE: not-empty (!isEmpty())

  protected BoundedBuffer(int capacity) {
    super(capacity);
  }

  // BLOCKS-UNTIL: not-full
  public synchronized void put(V v) throws InterruptedException {
    while (isFull()) {
      wait();
    }
    doPut(v);
    notifyAll();
  }

  // BLOCKS-UNTIL: not-empty
  public synchronized V take() throws InterruptedException {
    while (isEmpty()) {
      wait();
    }
    V v = doTake();
    notifyAll();
    return v;
  }
}
