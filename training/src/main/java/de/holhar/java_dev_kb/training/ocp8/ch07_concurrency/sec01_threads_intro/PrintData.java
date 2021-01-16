package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec01_threads_intro;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * Defining the task, or work, that a Thread instance will execute via a Runnable object or lambda expression to the
 * Thread constructor - MORE COMMON APPROACH.
 */
public class PrintData implements Runnable {

    public static void main(String[] args) {
        (new Thread(new PrintData())).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            println("Printing record: " + i);
        }
    }
}
