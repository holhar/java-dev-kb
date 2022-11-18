package de.holhar.java_dev_kb.training.concurrency.ch12_testing_concurrent_programs.s12_2_testing_for_performance.s12_2_1_extend_putTakeTest_by_timing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.holhar.java_dev_kb.training.concurrency.ch12_testing_concurrent_programs.s12_1_testing_for_correctness.BoundedBuffer;
import de.holhar.java_dev_kb.training.concurrency.ch12_testing_concurrent_programs.s12_1_testing_for_correctness.s12_1_3_testing_safety.RandomNumberGenerator;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Producer-consumer test program for BoundedBuffer.
 */
public class TimedPutTakeTest {

  //private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TimedPutTakeTest.class);

  private static final ExecutorService pool = Executors.newCachedThreadPool();

  private final AtomicInteger putSum = new AtomicInteger(0);
  private final AtomicInteger takeSum = new AtomicInteger(0);

  private final BarrierTimer timer;
  private final CyclicBarrier barrier;
  private final BoundedBuffer<Integer> bb;
  private final int nTrials, nPairs;

  /*
   * Driver program for TimedPutTakeTest.
   */
  public static void main(String[] args) throws Exception {
    int tpt = 100_000; // Trials per thread
    for (int cap = 1; cap <= 1_000; cap *= 10) {
      //logger.info("Capacity: {}", cap);
      for (int pairs = 1; pairs <= 128; pairs *= 2) {
        TimedPutTakeTest t = new TimedPutTakeTest(cap, pairs, tpt);
        //logger.info("Paris: {}\t");
        t.test();
        //logger.into("\t");
        Thread.sleep(1000);
        t.test();
        //logger.into("");
        Thread.sleep(1000);
      }
    }
    pool.shutdown();
  }

  public TimedPutTakeTest(int capacity, int npairs, int ntrials) {
    this.bb = new BoundedBuffer<>(capacity);
    this.nTrials = ntrials;
    this.nPairs = npairs;
    this.timer = new BarrierTimer();
    this.barrier = new CyclicBarrier(npairs * 2 + 1, timer);
  }

  /*
   * Testing with a barrier-based timer.
   */
  void test() {
    try {
      timer.clear();
      for (int i = 0; i < nPairs; i++) {
        pool.execute(new Producer());
        pool.execute(new Consumer());
      }
      barrier.await(); // wait for all threads to be ready
      barrier.await(); // wait for all threads to be finish
      long nsPerItem = timer.getTime() / (nPairs * (long)nTrials);
      //logger.info("Throughput: {} ns/itme", nsPerItem);
      assertEquals(putSum.get(), takeSum.get());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /*
   * Producer and consumer classes used in TimedPutTakeTest.
   */
  class Producer implements Runnable {

    @Override
    public void run() {
      try {
        int seed = (this.hashCode() ^ (int)System.nanoTime());
        int sum = 0;
        barrier.await();
        for (int i = nTrials; i > 0; --i) {
          bb.put(seed);
          sum += seed;
          seed = RandomNumberGenerator.xorShift(seed);
        }
        putSum.getAndAdd(sum);
        barrier.await();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

  class Consumer implements Runnable {

    @Override
    public void run() {
      try {
        barrier.await();
        int sum = 0;
        for (int i = nTrials; i > 0; --i) {
          sum += bb.take();
        }
        takeSum.getAndAdd(sum);
        barrier.await();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
}
