package de.holhar.java_dev_kb.training.concurrency.ch08_applying_thread_pools.s8_3_configuring_thread_pool_executor.s8_3_4_thread_factories;

/**
 * (My)ThreadFactory interface (just showing it here as a reference).
 */
public interface MyThreadFactory {
  Thread newThread(Runnable r);
}
