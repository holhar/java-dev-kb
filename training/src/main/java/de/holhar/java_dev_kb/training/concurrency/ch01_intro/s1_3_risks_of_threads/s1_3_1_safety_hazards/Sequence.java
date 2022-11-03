package de.holhar.java_dev_kb.training.concurrency.ch01_intro.s1_3_risks_of_threads.s1_3_1_safety_hazards;

import de.holhar.java_dev_kb.training.concurrency.utils.GuardedBy;
import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;

/**
 * Thread-safe sequence generator.
 */
@ThreadSafe
public class Sequence {

    @GuardedBy("this")
    private int value;

    public synchronized int getNext() {
        return value++;
    }
}
