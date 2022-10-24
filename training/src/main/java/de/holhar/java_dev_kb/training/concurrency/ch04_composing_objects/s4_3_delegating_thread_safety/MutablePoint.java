package de.holhar.java_dev_kb.training.concurrency.ch04_composing_objects.s4_3_delegating_thread_safety;

import de.holhar.java_dev_kb.training.concurrency.utils.NotThreadSafe;

/**
 * Mutable point class similar to java.awt.Point.
 */
@NotThreadSafe
public class MutablePoint {

    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
}
