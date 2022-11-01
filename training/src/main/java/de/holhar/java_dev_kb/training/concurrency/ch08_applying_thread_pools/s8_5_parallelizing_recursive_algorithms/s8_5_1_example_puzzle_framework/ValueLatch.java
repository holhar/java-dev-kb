package de.holhar.java_dev_kb.training.concurrency.ch08_applying_thread_pools.s8_5_parallelizing_recursive_algorithms.s8_5_1_example_puzzle_framework;

import de.holhar.java_dev_kb.training.concurrency.utils.GuardedBy;
import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;
import java.util.concurrent.CountDownLatch;

/**
 * Result-bearing latch used by ConcurrentPuzzleSolver.
 */
@ThreadSafe
public class ValueLatch<T> {

  @GuardedBy("this")
  private T value = null;

  private final CountDownLatch done = new CountDownLatch(1);

  public boolean isSet() {
    return (done.getCount() == 0);
  }

  public synchronized void setValue(T newValue) {
    if (!isSet()) {
      value = newValue;
      done.countDown();
    }
  }

  public T getValue() throws InterruptedException {
    done.await();
    synchronized (this) {
      return value;
    }
  }
}
