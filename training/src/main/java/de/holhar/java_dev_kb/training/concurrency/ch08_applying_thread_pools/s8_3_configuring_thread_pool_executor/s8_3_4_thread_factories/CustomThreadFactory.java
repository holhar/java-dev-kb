package de.holhar.java_dev_kb.training.concurrency.ch08_applying_thread_pools.s8_3_configuring_thread_pool_executor.s8_3_4_thread_factories;

import de.holhar.java_dev_kb.training.concurrency.ch08_applying_thread_pools.s8_3_configuring_thread_pool_executor.s8_3_5_customizing_thread_pool_executor.MyAppThread;
import java.util.concurrent.ThreadFactory;

/**
 * Custom thread factory.
 */
public class CustomThreadFactory implements ThreadFactory {

  private final String poolName;

  public CustomThreadFactory(String poolName) {
    this.poolName = poolName;
  }

  @Override
  public Thread newThread(Runnable runnable) {
    return new MyAppThread(runnable, poolName);
  }
}
