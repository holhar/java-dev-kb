package de.holhar.java_dev_kb.training.concurrency.ch14_custom_synchronizers.s14_5_abstract_queued_synchronizer;

/**
 * Canonical forms for acquisition and release in AQS.
 */
public class AqsAcquisitionAndRelease {

  private boolean stateDoesNotPermitAcquire;
  private boolean blockingAcquisitionRequested;
  private boolean failure;
  private boolean success;

  private boolean newSateMayPermitABlockedThreadToAcquire;

  boolean acquire() throws InterruptedException {
    while (stateDoesNotPermitAcquire) {
      if (blockingAcquisitionRequested) {
        // Enqueue current thread if not already queued
        // Block current thread
      } else {
        return failure;
      }
    }
    // Possibly update synchronization state
    // Dequeue thread if it was queued
    return success;
  }

  void release() {
    // Update synchronization state
    if (newSateMayPermitABlockedThreadToAcquire) {
      // Unblock one or more queued threads
    }
  }
}
