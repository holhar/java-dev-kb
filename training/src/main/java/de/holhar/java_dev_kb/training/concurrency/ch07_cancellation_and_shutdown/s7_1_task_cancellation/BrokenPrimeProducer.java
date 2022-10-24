package de.holhar.java_dev_kb.training.concurrency.ch07_cancellation_and_shutdown.s7_1_task_cancellation;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * Unreliable cancellation that can leave producers stuck in a blocking operation.
 * Don't do this.
 *
 * See {@link PrimeApplication#consumePrimes()} for client code example.
 */
public class BrokenPrimeProducer extends Thread {

    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancelled = false;

    public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!cancelled) {
                // The queue might be blocked when full - cancel flag has no effect!
                queue.put(p = p.nextProbablePrime());
            }
        } catch (InterruptedException consumed) { }
    }

    public void cancel() {
        cancelled = true;
    }
}
