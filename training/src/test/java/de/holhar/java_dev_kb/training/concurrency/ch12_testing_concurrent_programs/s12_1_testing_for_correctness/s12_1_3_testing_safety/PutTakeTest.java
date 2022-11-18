package de.holhar.java_dev_kb.training.concurrency.ch12_testing_concurrent_programs.s12_1_testing_for_correctness.s12_1_3_testing_safety;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.holhar.java_dev_kb.training.concurrency.ch12_testing_concurrent_programs.s12_1_testing_for_correctness.BoundedBuffer;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Producer-consumer test program for BoundedBuffer.
 */
public class PutTakeTest {

  private static final ExecutorService pool = Executors.newCachedThreadPool();

  private final AtomicInteger putSum = new AtomicInteger(0);
  private final AtomicInteger takeSum = new AtomicInteger(0);

  private final CyclicBarrier barrier;
  private final BoundedBuffer<Integer> bb;
  private final int nTrials, nPairs;

  public static void main(String[] args) {
    new PutTakeTest(10, 10, 100_000).test(); // sample parameters
    pool.shutdown();
  }

  public PutTakeTest(int capacity, int npairs, int ntrials) {
    this.bb = new BoundedBuffer<>(capacity);
    this.nTrials = ntrials;
    this.nPairs = npairs;
    this.barrier = new CyclicBarrier(npairs * 2 + 1);
  }

  void test() {
    try {
      for (int i = 0; i < nPairs; i++) {
        pool.execute(new Producer());
        pool.execute(new Consumer());
      }
      barrier.await(); // wait for all threads to be ready
      barrier.await(); // wait for all threads to be finish
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
