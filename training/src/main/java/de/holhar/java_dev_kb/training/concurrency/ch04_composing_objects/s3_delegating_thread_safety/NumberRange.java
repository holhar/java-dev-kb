package de.holhar.java_dev_kb.training.concurrency.ch04_composing_objects.s3_delegating_thread_safety;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Number range class does not sufficiently protect its invariants.
 * Don't do this.
 */
public class NumberRange {

    // INVARIANT: lower <= upper
    private final AtomicLong lower = new AtomicLong(0);
    private final AtomicLong upper = new AtomicLong(0);

    public void setLower(int i) {
        // Warning -- unsafe check-then-act
        if (i > upper.get()) {
            throw new IllegalArgumentException("Can't set lower to " + i + " > upper");
        }
        lower.set(i);
    }

    public void setUpper(int i) {
        // Warning -- unsafe check-then-act
        if (i < lower.get()) {
            throw new IllegalArgumentException("Can't set upper to " + i + " < lower");
        }
        upper.set(i);
    }

    public boolean isInRange(int i) {
        return (i >= lower.get() && i <= upper.get());
    }
}
