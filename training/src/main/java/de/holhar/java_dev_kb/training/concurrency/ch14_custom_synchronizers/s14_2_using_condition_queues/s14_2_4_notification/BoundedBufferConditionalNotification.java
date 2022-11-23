package de.holhar.java_dev_kb.training.concurrency.ch14_custom_synchronizers.s14_2_using_condition_queues.s14_2_4_notification;

import de.holhar.java_dev_kb.training.concurrency.ch14_custom_synchronizers.s14_1_managin_state_dependence.BaseBoundedBuffer;

/**
 * Using conditional notification in BoundedBuffer.put.
 */
public class BoundedBufferConditionalNotification<V> extends BaseBoundedBuffer<V> {

  protected BoundedBufferConditionalNotification(int capacity) {
    super(capacity);
  }

  public synchronized void put(V v) throws InterruptedException {
    while (isFull()) {
      wait();
    }
    boolean wasEmpty = isEmpty();
    doPut(v);
    if (wasEmpty) {
      notifyAll();
    }
  }
}
