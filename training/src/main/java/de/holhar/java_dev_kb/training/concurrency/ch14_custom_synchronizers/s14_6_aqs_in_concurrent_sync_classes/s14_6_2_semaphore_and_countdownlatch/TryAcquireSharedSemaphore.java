package de.holhar.java_dev_kb.training.concurrency.ch14_custom_synchronizers.s14_6_aqs_in_concurrent_sync_classes.s14_6_2_semaphore_and_countdownlatch;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * tryAcquireShared and tryReleaseShared from Semaphore.
 */
public class TryAcquireSharedSemaphore extends AbstractQueuedSynchronizer {

  protected int tryAcquireShared(int acquires) {
    while (true) {
      int available = getState();
      int remaining = available - acquires;
      if (remaining < 0 || compareAndSetState(available, remaining)) {
        return remaining;
      }
    }
  }

  protected boolean tryReleaseShared(int releases) {
    while (true) {
      int p = getState();
      if (compareAndSetState(p, p + releases)) {
        return true;
      }
    }
  }
}
