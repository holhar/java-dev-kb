package de.holhar.java_dev_kb.training.concurrency.ch14_custom_synchronizers.s14_1_managin_state_dependence.s14_1_2_example_crude_blocking;

import de.holhar.java_dev_kb.training.concurrency.ch14_custom_synchronizers.s14_1_managin_state_dependence.BaseBoundedBuffer;
import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;

/**
 * Bounded buffer using crude blocking by polling and sleeping (busy-waiting).
 * NOT IDEAL.
 */
@ThreadSafe
public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

  private static final long SLEEP_GRANULARITY = 1000;

  public SleepyBoundedBuffer(int capacity) {
    super(capacity);
  }

  public void put(V v) throws InterruptedException {
    while (true) {
      synchronized (this) {
        if (!isFull()) {
          doPut(v);
          return;
        }
      }
      Thread.sleep(SLEEP_GRANULARITY);
    }
  }

  public V take() throws InterruptedException {
    while (true) {
      synchronized (this) {
        if (!isEmpty()) {
          return doTake();
        }
      }
      Thread.sleep(SLEEP_GRANULARITY);
    }
  }
}
