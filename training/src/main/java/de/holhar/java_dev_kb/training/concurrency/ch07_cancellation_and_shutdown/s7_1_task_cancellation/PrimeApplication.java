package de.holhar.java_dev_kb.training.concurrency.ch07_cancellation_and_shutdown.s7_1_task_cancellation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Generating a second's worth of prime numbers.
 */
public class PrimeApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrimeApplication.class);

    public static void main(String[] args) throws InterruptedException {
        LOGGER.info("{}", new PrimeApplication().aSecondOfPrimes());
    }

    public List<BigInteger> aSecondOfPrimes() throws InterruptedException {
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();
        try {
            Thread.sleep(1000);
        } finally {
            generator.cancel();
        }
        return generator.get();
    }

    public void consumePrimes() throws InterruptedException {
        BlockingQueue<BigInteger> primes = new LinkedBlockingQueue<>();
        BrokenPrimeProducer producer = new BrokenPrimeProducer(primes);
        producer.start();
        try {
            while (needMorePrimes()) {
                consume(primes.take());
            }
        } finally {
            producer.cancel();
        }
    }

    private void consume(BigInteger prime) {
        // ...
    }

    private boolean needMorePrimes() {
        // ...
        return true;
    }
}
