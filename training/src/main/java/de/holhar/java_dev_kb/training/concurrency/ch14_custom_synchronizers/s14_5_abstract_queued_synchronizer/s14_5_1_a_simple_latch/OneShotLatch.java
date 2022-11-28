package de.holhar.java_dev_kb.training.concurrency.ch14_custom_synchronizers.s14_5_abstract_queued_synchronizer.s14_5_1_a_simple_latch;

import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Binary latch using AbstractQueuedSynchronizer.
 */
@ThreadSafe
public class OneShotLatch {

  private final Sync sync = new Sync();

  public void signal() {
    sync.releaseShared(0);
  }

  public void await() throws InterruptedException {
    sync.acquireSharedInterruptibly(0);
  }

  private class Sync extends AbstractQueuedSynchronizer {
    protected int tryAcquireShared(int ignored) {
      // Succeed if latch is open (state == 1), else fail
      return (getState() == 1) ? 1: -1;
    }

    protected boolean tryReleaseShared(int ignored) {
      setState(1); // Latch is now open
      return true; // Other threads my now be able to acquire
    }
  }
}
