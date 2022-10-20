package de.holhar.java_dev_kb.training.concurrency.ch07_cancellation_and_shutdown.s2_stopping_thread_based_service.s7_2_3_poison_pills;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * Shutdown with poison pill.
 */
public class IndexingService {

  public static final File POISON = new File("");
  private final IndexerThread consumer;
  private final CrawlerThread producer;
  private final BlockingQueue<File> queue;
  private final FileFilter fileFilter;
  private final File root;

  public IndexingService(BlockingQueue<File> queue, FileFilter fileFilter, File root) {
    this.queue = queue;
    this.fileFilter = fileFilter;
    this.root = root;
    this.producer = new CrawlerThread(queue, root);
    this.consumer = new IndexerThread(queue);
  }

  public void start() {
    producer.start();
    consumer.start();
  }

  public void stop() {
    producer.interrupt();
  }

  public void awaitTermination() throws InterruptedException {
    consumer.join();
  }
}
