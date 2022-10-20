package de.holhar.java_dev_kb.training.concurrency.ch07_cancellation_and_shutdown.s2_stopping_thread_based_service.s7_2_2_executor_service_shutdown;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

import de.holhar.java_dev_kb.training.concurrency.utils.GuardedBy;
import java.io.PrintWriter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Logging service that uses an ExecutorService
 */
public class LogServiceWithExecutor {

  private final ExecutorService exec = newSingleThreadExecutor();
  private final WriteTask writeTask;
  private final PrintWriter writer;

  public LogServiceWithExecutor(WriteTask writeTask, PrintWriter writer) {
    this.writeTask = writeTask;
    this.writer = writer;
  }

  public void start() {
    writeTask.start();
  }

  public void stop() {
    try {
      exec.shutdown();
      exec.awaitTermination(10_000, TimeUnit.MILLISECONDS);
    } catch (InterruptedException ignored) {
    } finally {
      writer.close();
    }
  }

  public void log(String msg) throws InterruptedException {
    try {
      exec.execute(new WriteTask(msg));
    } catch (RejectedExecutionException ignored) {
    }
  }

  private class WriteTask extends Thread {

    private final String msg;

    public WriteTask(String msg) {
      this.msg = msg;
    }

    public void run() {
      try {
        writer.println(msg);
      } finally {
        writer.close();
      }
    }
  }
}
