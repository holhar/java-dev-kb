package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec01_threads_intro;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class CheckResults {

    private static int counter;

    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 0; i < 500_000; i++) {
                CheckResults.counter++;
            }
        }).start();

        // Constant polling - a No-No
        while (CheckResults.counter < 300_000) {
            println("Not reached yet!");
        }
        println("REACHED!");
    }
}
