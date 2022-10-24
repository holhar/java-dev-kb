package de.holhar.java_dev_kb.training.concurrency.ch07_cancellation_and_shutdown.s7_2_stopping_thread_based_service.s7_2_3_poison_pills;

import static de.holhar.java_dev_kb.training.concurrency.ch07_cancellation_and_shutdown.s7_2_stopping_thread_based_service.s7_2_3_poison_pills.IndexingService.POISON;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * Consumer thread for IndexingService.
 */
public class IndexerThread extends Thread {

  private final BlockingQueue<File> queue;

  public IndexerThread(BlockingQueue<File> queue) {
    this.queue = queue;
  }

  public void run() {
    try {
      while (true) {
        File file = queue.take();
        if (file == POISON) {
          break;
        } else {
          indexFile(file);
        }
      }
    } catch (InterruptedException consumed) {}
  }

  private void indexFile(File file) {
    // ...
  }
}
