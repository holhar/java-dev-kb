package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec01_threads_intro;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * @author hhs@dasburo.com
 * <p>
 * Defining the task, or work, that a Thread instance will execute via a class extending Thread and overriding run()
 * method - LESS COMMON APPROACH.
 */
public class ReadInventoryThread extends Thread {

    public static void main(String[] args) {
        (new ReadInventoryThread()).start();
    }

    @Override
    public void run() {
        println("Printing zoo inventory");
    }
}
