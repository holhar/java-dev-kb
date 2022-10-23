package de.holhar.java_dev_kb.training.concurrency.ch07_cancellation_and_shutdown.s3_handling_abnormal_thread_termination;

/**
 * Typical thread-pool worker thread structure.
 */
public class HandleAbnormalThreadTermination extends Thread {

  public void run() {
    Throwable thrown = null;
    try {
      while (!isInterrupted()) {
        runTask(getTaskFromWorkerQueue());
      }
    } catch (Throwable e) {
      thrown = e;
    } finally {
      threadExited(this, thrown);
    }
  }

  private void runTask(Runnable task) {
    // ...
  }

  private Runnable getTaskFromWorkerQueue() {
    // ...
    return null;
  }

  private void threadExited(Thread thread, Throwable thrown) {
    // ...
  }
}
