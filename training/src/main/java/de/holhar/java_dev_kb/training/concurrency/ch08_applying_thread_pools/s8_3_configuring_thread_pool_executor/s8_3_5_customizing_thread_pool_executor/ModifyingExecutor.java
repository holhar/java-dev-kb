package de.holhar.java_dev_kb.training.concurrency.ch08_applying_thread_pools.s8_3_configuring_thread_pool_executor.s8_3_5_customizing_thread_pool_executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Modifying an Executor created with the standard factories.
 */
public class ModifyingExecutor {

  private final ExecutorService exec = Executors.newCachedThreadPool();

  public void modifyExecutor() {
    if (exec instanceof ThreadPoolExecutor) {
      ((ThreadPoolExecutor)exec).setCorePoolSize(10);
    } else {
      throw new AssertionError("Oops, bad assumption");
    }
  }

}
