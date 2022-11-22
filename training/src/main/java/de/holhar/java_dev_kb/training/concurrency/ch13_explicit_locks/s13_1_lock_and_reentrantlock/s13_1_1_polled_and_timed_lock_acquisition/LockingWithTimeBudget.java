package de.holhar.java_dev_kb.training.concurrency.ch13_explicit_locks.s13_1_lock_and_reentrantlock.s13_1_1_polled_and_timed_lock_acquisition;

import de.holhar.java_dev_kb.training.concurrency.ch13_explicit_locks.s13_1_lock_and_reentrantlock.s13_1_2_interruptible_lock_acquisition.InterruptibleLockAcquisition;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Locking with a time budget.
 */
public class LockingWithTimeBudget {

  private Lock lock = new ReentrantLock();

  public boolean trySendOnSharedLine(String message, long timeout, TimeUnit unit) throws InterruptedException {
    long nanosToLock = unit.toNanos(timeout) - estimatedNanosToSend(message);

    if (!lock.tryLock(nanosToLock, TimeUnit.NANOSECONDS)) {
      return false;
    }

    try {
      return InterruptibleLockAcquisition.sendOnSharedLine(message);
    } finally {
      lock.unlock();
    }
  }

  private long estimatedNanosToSend(String message) {
    return 0;
  }
}
