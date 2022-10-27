package de.holhar.java_dev_kb.training.concurrency.ch08_applying_thread_pools.s8_3_configuring_thread_pool_executor.s8_3_5_customizing_thread_pool_executor;

import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom thread base class.
 */
public class MyAppThread extends Thread {

  private static final Logger logger = LoggerFactory.getLogger(MyAppThread.class);

  public static final String DEFAULT_NAME = "MyAppThread";
  private static volatile boolean debugLifeCycle = false;
  private static final AtomicInteger created = new AtomicInteger();
  private static final AtomicInteger alive = new AtomicInteger();

  public MyAppThread(Runnable runnable) {
    this(runnable, DEFAULT_NAME);
  }

  public MyAppThread(Runnable runnable, String name) {
    super(runnable, name + "-" + created.incrementAndGet());

    setUncaughtExceptionHandler(
        (t, e) -> logger.error("UNCAUGHT in thread " + t.getName(), e)
    );
  }

  @Override
  public void run() {
    // Copy debug flag to ensure consistent value throughout.
    boolean debug = debugLifeCycle;
    if (debug) {
      logger.debug("Created {}", getName());
    }

    try {
      alive.incrementAndGet();
      super.run();
    } finally {
      alive.decrementAndGet();
      if (debug) {
        logger.debug("Exiting {}", getName());
      }
    }
  }
}
