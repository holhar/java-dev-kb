package de.holhar.java_dev_kb.training.concurrency.ch04_composing_objects.s1_thread_safe_class_design;

import de.holhar.java_dev_kb.training.concurrency.utils.GuardedBy;
import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;

/**
 * Simple thread-safe counter using the Java monitor pattern.
 */
@ThreadSafe
public final class Counter {

    @GuardedBy("this")
    private long value = 0;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment() {
        if (value == Long.MAX_VALUE) {
            throw new IllegalArgumentException("counter overflow");
        }
        return ++value;
    }
}
