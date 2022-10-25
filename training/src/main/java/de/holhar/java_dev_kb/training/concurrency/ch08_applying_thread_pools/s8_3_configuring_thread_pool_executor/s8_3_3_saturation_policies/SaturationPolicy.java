package de.holhar.java_dev_kb.training.concurrency.ch08_applying_thread_pools.s8_3_configuring_thread_pool_executor.s8_3_3_saturation_policies;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Creating a fixed-sized thread pool with a bounded queue and the caller-runs saturation policy.
 */
public class SaturationPolicy {

  private static final int N_THREADS = 100;
  private static final int OL = 10000;
  private static final int CAPACITY = 1000;

  private static ThreadPoolExecutor executor = new ThreadPoolExecutor(
      N_THREADS,
      N_THREADS,
      OL,
      TimeUnit.MILLISECONDS,
      new LinkedBlockingQueue<>(CAPACITY)
  );

  static {
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
  }

}
