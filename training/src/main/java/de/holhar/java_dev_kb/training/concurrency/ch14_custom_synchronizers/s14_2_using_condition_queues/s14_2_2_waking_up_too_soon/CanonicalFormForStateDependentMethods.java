package de.holhar.java_dev_kb.training.concurrency.ch14_custom_synchronizers.s14_2_using_condition_queues.s14_2_2_waking_up_too_soon;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Canonical form for state-dependent methods.
 */
public class CanonicalFormForStateDependentMethods {

  private Lock lock = new ReentrantLock();

  void stateDependentMethod() throws InterruptedException {
    // Condition predicate must be guarded by lock
    synchronized(lock) {
      while (!conditionPredicate()) {
        lock.wait();
      }
      // Object is now in desired state
    }
  }

  private boolean conditionPredicate() {
    return false;
  }
}
