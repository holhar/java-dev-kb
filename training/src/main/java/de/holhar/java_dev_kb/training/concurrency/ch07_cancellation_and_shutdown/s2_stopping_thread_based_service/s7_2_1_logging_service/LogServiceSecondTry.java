package de.holhar.java_dev_kb.training.concurrency.ch07_cancellation_and_shutdown.s2_stopping_thread_based_service.s7_2_1_logging_service;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

/**
 * Unreliable way to add shutdown support to the logging service :|
 */
public class LogServiceSecondTry {

  private final BlockingQueue<String> queue;
  private final LoggerThread loggerThread;
  private final PrintWriter writer;

  private boolean isShutDownRequested;

  public LogServiceSecondTry(BlockingQueue<String> queue, LoggerThread loggerThread, PrintWriter writer) {
    this.queue = queue;
    this.loggerThread = loggerThread;
    this.writer = writer;
  }

  public void start() {
    loggerThread.start();
  }

  public void log(String msg) throws InterruptedException {
    if (!isShutDownRequested) {
      queue.put(msg);
    } else {
      throw new IllegalArgumentException("logger is shut down");
    }
  }

  private class LoggerThread extends Thread {

    public void run() {
      try {
        while (true) {
          writer.println(queue.take());
        }
      } catch (InterruptedException ignored) {
      } finally {
        writer.close();
      }
    }
  }
}
