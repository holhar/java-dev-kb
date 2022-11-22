package de.holhar.java_dev_kb.training.concurrency.ch13_explicit_locks.s13_1_lock_and_reentrantlock.s13_1_2_interruptible_lock_acquisition;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Interruptible lock acquisition.
 */
public class InterruptibleLockAcquisition {

  private static Lock lock = new ReentrantLock();

  public static boolean sendOnSharedLine(String message) throws InterruptedException {
    lock.lockInterruptibly();
    try {
      return cancellableSendOnSharedLine(message);
    } finally {
      lock.unlock();
    }
  }

  private static boolean cancellableSendOnSharedLine(String message) throws InterruptedException{
    return false;
  }
}
