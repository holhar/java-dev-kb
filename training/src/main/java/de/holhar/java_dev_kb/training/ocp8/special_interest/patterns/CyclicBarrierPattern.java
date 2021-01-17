package de.holhar.java_dev_kb.training.ocp8.special_interest.patterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierPattern {

    private static final Logger LOGGER = LoggerFactory.getLogger(CyclicBarrierPattern.class);

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(4);
            CyclicBarrier c1 = new CyclicBarrier(4);
            CyclicBarrier c2 = new CyclicBarrier(4, () -> LOGGER.debug("Almost done!"));
            CyclicBarrierPattern pattern = new CyclicBarrierPattern();
            for (int i = 0; i < 4; i++) {
                service.submit(() -> pattern.performTask(c1, c2));
            }
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }

    private void doSomething() {
        LOGGER.debug("Do something...");
    }

    private void dumDiDum() {
        LOGGER.debug("Dum di dum!");
    }

    private void finishIt() {
        LOGGER.debug("Last thing to do.");
    }

    public void performTask(CyclicBarrier c1, CyclicBarrier c2) {
        try {
            doSomething();
            c1.await();
            dumDiDum();
            c2.await();
            finishIt();
        } catch (BrokenBarrierException | InterruptedException e) {
            // Handle Exceptions...
        }
    }
}
