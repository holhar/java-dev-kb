package de.holhar.java_dev_kb.training.concurrency.ch07_cancellation_and_shutdown.s7_2_stopping_thread_based_service.s7_2_1_logging_service;

import de.holhar.java_dev_kb.training.concurrency.utils.GuardedBy;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

/**
 * Adding reliable cancellation to LogService :)
 */
public class LogServiceThirdTry {

  private final BlockingQueue<String> queue;
  private final LoggerThread loggerThread;
  private final PrintWriter writer;

  @GuardedBy("this")
  private boolean isShutDown;

  @GuardedBy("this")
  private int reservations;

  public LogServiceThirdTry(BlockingQueue<String> queue, LoggerThread loggerThread, PrintWriter writer) {
    this.queue = queue;
    this.loggerThread = loggerThread;
    this.writer = writer;
  }

  public void start() {
    loggerThread.start();
  }

  public void stop() {
    synchronized (this) {
      isShutDown = true;
    }
    loggerThread.interrupt();
  }

  public void log(String msg) throws InterruptedException {
    synchronized (this) {
      if (isShutDown) {
        throw new IllegalArgumentException("logger is shut down");
      }
      ++reservations;
    }
    queue.put(msg);
  }

  private class LoggerThread extends Thread {

    public void run() {
      try {
        while (true) {
          try {
            synchronized (LogServiceThirdTry.this) {
              if (isShutDown && reservations == 0) {
                break;
              }
            }
            String msg = queue.take();
            synchronized (LogServiceThirdTry.this) {
              --reservations;
            }
            writer.println(msg);
          } catch (InterruptedException ignored) {
            /* retry */
          }
        }
      } finally {
        writer.close();
      }
    }
  }
}
