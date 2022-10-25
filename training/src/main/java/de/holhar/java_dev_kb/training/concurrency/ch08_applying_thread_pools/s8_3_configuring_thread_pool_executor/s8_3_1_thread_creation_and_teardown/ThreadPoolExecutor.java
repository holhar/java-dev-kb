package de.holhar.java_dev_kb.training.concurrency.ch08_applying_thread_pools.s8_3_configuring_thread_pool_executor.s8_3_1_thread_creation_and_teardown;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * General constructor for ThreadPoolExecutor (just showing it here as a reference).
 */
public class ThreadPoolExecutor {

  public ThreadPoolExecutor(
      int corePoolSize,
      int maximumPoolSize,
      long keepAliveTime,
      TimeUnit unit,
      BlockingQueue<Runnable> workQueue,
      ThreadFactory threadFactory,
      RejectedExecutionHandler handler
  ) {
    // ...
  }
}
