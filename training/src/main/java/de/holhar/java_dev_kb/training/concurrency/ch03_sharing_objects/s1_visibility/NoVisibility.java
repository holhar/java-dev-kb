package de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.s1_visibility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sharing variables without synchronization.
 * Don't do this.
 */
public class NoVisibility {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoVisibility.class);

    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            // Might loop forever!
            while (!ready) {
                Thread.yield();
            }
            // Might print zero!
            LOGGER.info("{}", number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
