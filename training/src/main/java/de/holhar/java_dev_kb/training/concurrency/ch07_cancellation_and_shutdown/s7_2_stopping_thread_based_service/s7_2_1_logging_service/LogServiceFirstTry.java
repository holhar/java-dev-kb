package de.holhar.java_dev_kb.training.concurrency.ch07_cancellation_and_shutdown.s7_2_stopping_thread_based_service.s7_2_1_logging_service;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

/**
 * Producer-consumer logging service with no shutdown support :|
 */
public class LogServiceFirstTry {

  private final BlockingQueue<String> queue;
  private final LoggerThread loggerThread;
  private final PrintWriter writer;

  public LogServiceFirstTry(BlockingQueue<String> queue, LoggerThread loggerThread, PrintWriter writer) {
    this.queue = queue;
    this.loggerThread = loggerThread;
    this.writer = writer;
  }

  public void start() {
    loggerThread.start();
  }

  public void log(String msg) throws InterruptedException {
    queue.put(msg);
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
