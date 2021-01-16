package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec01_threads_intro;

/**
 * Creating Runnable classes.
 *
 * It is also useful if you need to pass information to your Runnable object to be used by the
 * run() method, such as in the following class constructor
 */
public class CalculateAverage implements Runnable {

    private int[] scores;

    public CalculateAverage(int[] scores) {
        this.scores = scores;
    }

    @Override
    public void run() {
        // Define work here, that uses the scores object
    }
}
