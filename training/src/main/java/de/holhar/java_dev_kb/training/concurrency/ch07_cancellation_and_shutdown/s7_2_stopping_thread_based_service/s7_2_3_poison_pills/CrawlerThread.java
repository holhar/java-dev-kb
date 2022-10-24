package de.holhar.java_dev_kb.training.concurrency.ch07_cancellation_and_shutdown.s7_2_stopping_thread_based_service.s7_2_3_poison_pills;

import static de.holhar.java_dev_kb.training.concurrency.ch07_cancellation_and_shutdown.s7_2_stopping_thread_based_service.s7_2_3_poison_pills.IndexingService.POISON;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * Producer thread for IndexingService.
 */
public class CrawlerThread extends Thread {

  private final BlockingQueue<File> queue;
  private final File root;

  public CrawlerThread(BlockingQueue<File> queue, File root) {
    this.queue = queue;
    this.root = root;
  }

  public void run() {
    try {
      crawl(root);
    } catch (InterruptedException e) {
      /* fall through */
    } finally {
      while (true) {
        try {
          queue.put(POISON);
          break;
        } catch (InterruptedException e1) {
          /* retry */
        }
      }
    }
  }

  private void crawl(File root) throws InterruptedException {
    // ...
  }
}
