package de.holhar.java_dev_kb.training.concurrency.ch14_custom_synchronizers.s14_6_aqs_in_concurrent_sync_classes.s14_6_1_reentrantlock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * tryAcquire implementation from nonfair ReentrantLock.
 */
public class TryAcquireReentrantLock extends AbstractQueuedSynchronizer {

  private Thread owner;

  protected boolean tryAcquire(int ignored) {
    final Thread current = Thread.currentThread();
    int c = getState();
    if (c == 0) {
      if (compareAndSetState(0, 1)) {
        owner = current;
        return true;
      }
    } else if (current == owner) {
      setState(c+1);
      return true;
    }
    return false;
  }

}
