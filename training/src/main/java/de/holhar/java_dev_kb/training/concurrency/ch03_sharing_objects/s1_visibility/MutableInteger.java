package de.holhar.java_dev_kb.training.concurrency.ch03_sharing_objects.s1_visibility;

import de.holhar.java_dev_kb.training.concurrency.utils.NotThreadSafe;

/**
 * Non-thread-safe mutable integer holder.
 */
@NotThreadSafe
public class MutableInteger {
    private int value;

    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }
}
