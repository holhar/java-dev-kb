package de.holhar.java_dev_kb.training.effectivejava.item66;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Broken! - How long would you expect this program to run?
 */
public class BrokenStopThread {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrokenStopThread.class);

    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stopRequested) {
                    i++;
                    System.out.print(i + ", ");
                    if (i % 100 == 0)
                        LOGGER.debug("");
                }
            }
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}
