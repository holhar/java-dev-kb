package de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.s3_1_visibility;

/**
 * Counting sheep
 */
public class SheepCounter {

    public static volatile boolean asleep;

    public static void main(String[] args) {
        while (!asleep) {
            countSomeSheep();
        }
    }

    private static void countSomeSheep() {
        // counting...
    }
}
