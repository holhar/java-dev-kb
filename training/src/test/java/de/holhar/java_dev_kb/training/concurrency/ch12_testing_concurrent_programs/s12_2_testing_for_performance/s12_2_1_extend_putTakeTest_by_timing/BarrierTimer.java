package de.holhar.java_dev_kb.training.concurrency.ch12_testing_concurrent_programs.s12_2_testing_for_performance.s12_2_1_extend_putTakeTest_by_timing;

public class BarrierTimer implements Runnable {

  private boolean started;
  private long startTime, endTime;

  @Override
  public void run() {
    long t = System.nanoTime();
    if (!started) {
      started = true;
      startTime = t;
    } else {
      endTime = t;
    }
  }

  public synchronized void clear() {
    started = false;
  }

  public synchronized long getTime() {
    return endTime - startTime;
  }
}
