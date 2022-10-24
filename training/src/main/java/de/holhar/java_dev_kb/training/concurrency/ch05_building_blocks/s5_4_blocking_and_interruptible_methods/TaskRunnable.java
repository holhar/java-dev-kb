package de.holhar.java_dev_kb.training.concurrency.ch05_building_blocks.s5_4_blocking_and_interruptible_methods;

import org.springframework.scheduling.config.Task;

import java.util.concurrent.BlockingQueue;

/**
 * Restoring the interrupted status so as not to swallow the interrupted.
 */
public class TaskRunnable implements Runnable {

    BlockingQueue<Task> queue;
    //...

    public void run() {
        try {
            processTask(queue.take());
        } catch (InterruptedException e) {
            // Restore interrupted status
            Thread.currentThread().interrupt();
        }
    }

    private void processTask(Task task) {
        // do something...
    }
}
