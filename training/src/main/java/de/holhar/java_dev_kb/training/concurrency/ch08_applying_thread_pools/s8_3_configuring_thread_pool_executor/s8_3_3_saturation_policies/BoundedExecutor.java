package de.holhar.java_dev_kb.training.concurrency.ch08_applying_thread_pools.s8_3_configuring_thread_pool_executor.s8_3_3_saturation_policies;

import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/**
 * Using a Semaphore to throttle task submission.
 */
@ThreadSafe
public class BoundedExecutor {

  private final Executor exec;
  private final Semaphore semaphore;

  public BoundedExecutor(Executor exec, int bound) {
    this.exec = exec;
    this.semaphore = new Semaphore(bound);
  }

  public void submitTask(final Runnable command) {
    try {
      exec.execute(() -> {
        try {
          command.run();
        } finally {
          semaphore.release();
        }
      });
    } catch (RejectedExecutionException e) {
      semaphore.release();
    }
  }
}
