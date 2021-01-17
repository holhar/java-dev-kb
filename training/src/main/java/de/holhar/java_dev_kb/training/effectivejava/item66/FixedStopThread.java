package de.holhar.java_dev_kb.training.effectivejava.item66;

import de.holhar.java_dev_kb.training.effectivejava.item36.Bigram;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Properly synchronized cooperative thread termination
 */
public class FixedStopThread {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bigram.class);

    private static boolean stopRequested;

    private static synchronized void requestStop() {
        stopRequested = true;
    }

    private static synchronized boolean isStopRequested() {
        return stopRequested;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!isStopRequested()) {
                    i++;
                    LOGGER.debug(i + ", ");
                    if (i % 100 == 0)
                        LOGGER.debug("");
                }
            }
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        requestStop();
    }
}
