package de.holhar.java_dev_kb.training.concurrency.ch01_intro.s1_3_risks_of_threads.s1_3_1_safety_hazards;

import de.holhar.java_dev_kb.training.concurrency.utils.NotThreadSafe;

/**
 * Non-thread-safe sequence generator.
 */
@NotThreadSafe
public class UnsafeSequence {

    private int value;

    /** Returns a unique value. */
    public int getNext() {
        // Appears to be one operation, actually it's three -> not thread safe!
        return value++;
    }
}
