package de.holhar.java_dev_kb.training.concurrency.ch05_building_blocks.s5_3_blocking_queues;

import java.io.File;
import java.util.concurrent.BlockingQueue;
/**
 * Consumer tasks in a desktop search application.
 */
public class Indexer implements Runnable {

    private final BlockingQueue<File> queue;

    public Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            while (true) {
                indexFile(queue.take());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void indexFile(File file) {
        // ...
    }
}
