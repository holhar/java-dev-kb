package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec01_threads_intro;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * @author hhs@dasburo.com
 */
public class CheckResultsWithSleep {

    private static int counter;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            for (int i = 0; i < 500_000; i++) {
                CheckResultsWithSleep.counter++;
            }
        }).start();

        // Polling interrupted with sleep - a bit better...
        while (CheckResultsWithSleep.counter < 300_000) {
            println("Not reached yet!");
            Thread.sleep(1000);
        }
        println("REACHED!");
    }
}
