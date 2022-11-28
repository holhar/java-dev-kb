package de.holhar.java_dev_kb.training.concurrency.ch14_custom_synchronizers.s14_4_anatomy_of_a_synchronizer;

import de.holhar.java_dev_kb.training.concurrency.utils.GuardedBy;
import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Counting semaphore implemented using Lock.
 * (Not really how java.util.concurrent.Semaphore is implemented)
 */
@ThreadSafe
public class SemaphoreOnLock {

  private final Lock lock = new ReentrantLock();

  // CONDITION-PREDICATE: permitsAvailable (permits > 0)
  private final Condition permitsAvailable = lock.newCondition();

  @GuardedBy("lock")
  private int permits;

  SemaphoreOnLock(int initialPermits) {
    lock.lock();
    try {
      permits = initialPermits;
    } finally {
      lock.unlock();
    }
  }

  // BLOCKS-UNTIL: permitsAvailable
  public void acquire() throws InterruptedException {
    lock.lock();
    try {
      while (permits <= 0) {
        permitsAvailable.await();
      }
      --permits;
    } finally {
      lock.unlock();
    }
  }

  public void release() {
    lock.lock();
    try {
      ++permits;
      permitsAvailable.signal();
    } finally {
      lock.unlock();
    }
  }
}
