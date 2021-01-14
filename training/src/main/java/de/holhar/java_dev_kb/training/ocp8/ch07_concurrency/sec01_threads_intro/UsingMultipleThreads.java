package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec01_threads_intro;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * @author hhs@dasburo.com
 */
public class UsingMultipleThreads {

    public static void main(String[] args) {
        println("begin");
        (new ReadInventoryThread()).start();
        (new Thread(new PrintData())).start();
        (new ReadInventoryThread()).start();
        println("end");
    }
}
