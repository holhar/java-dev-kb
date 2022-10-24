package de.holhar.java_dev_kb.training.concurrency.ch07_cancellation_and_shutdown.s7_2_stopping_thread_based_service.s7_2_5_shutdown_now_limitations;

import de.holhar.java_dev_kb.training.concurrency.utils.GuardedBy;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public abstract class WebCrawler {

  private volatile TrackingExecutor exec;

  @GuardedBy("this")
  private final Set<URL> urlsToCrawl = new HashSet<>();

  public synchronized void start() {
    exec = new TrackingExecutor(Executors.newCachedThreadPool());
    for (URL url : urlsToCrawl) {
        submitCrawlTask(url);
    }
    urlsToCrawl.clear();
  }

  /*
   * This is where the magic happens:
   */
  public synchronized void stop() throws InterruptedException {
      try {
          saveUnUncrawled(exec.shutdownNow());
          if (exec.awaitTermination(30, TimeUnit.SECONDS)) {
              saveUnUncrawled(exec.getCancelledTasks());
          }
      } finally {
          exec = null;
      }
  }

  protected abstract List<URL> processPage(URL url);

  private void saveUnUncrawled(List<Runnable> uncrawled) {
      for (Runnable task : uncrawled) {
          urlsToCrawl.add(((CrawlTask) task).getPage());
      }
  }

  private void submitCrawlTask(URL u) {
    exec.execute(new CrawlTask(u));
  }

  private class CrawlTask implements Runnable {

    private final URL url;

    public CrawlTask(URL url) {
      this.url = url;
    }

    public void run() {
        for (URL link : processPage(url)) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            submitCrawlTask(link);
        }
    }

    public URL getPage() {
        return url;
    }
  }
}
