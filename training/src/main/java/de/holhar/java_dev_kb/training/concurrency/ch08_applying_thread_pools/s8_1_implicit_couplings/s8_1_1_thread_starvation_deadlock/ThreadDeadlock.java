package de.holhar.java_dev_kb.training.concurrency.ch08_applying_thread_pools.s8_1_implicit_couplings.s8_1_1_thread_starvation_deadlock;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Task that deadlocks in a single-threaded Executor.
 * DON'T DO THIS!
 */
public class ThreadDeadlock {

  private final ExecutorService exec = Executors.newSingleThreadExecutor();
  private String page;


  public class RenderPageTask implements Callable<String> {

    public String call() throws Exception {
      Future<String> header, footer;
      header = exec.submit(new LoadFileTask("header.html"));
      footer = exec.submit(new LoadFileTask("footer.html"));
      renderBody();
      // Will deadlock -- task waiting for result of subtask
      return header.get() + page + footer.get();
    }
  }

  public static class LoadFileTask implements Callable<String> {

    private final String segment;

    public LoadFileTask(String segment) {
      this.segment = segment;
    }

    @Override
    public String call() throws Exception {
      // ...
      return null;
    }
  }

  private void renderBody() {
    // ...
  }
}
