package de.holhar.java_dev_kb.training.concurrency.ch04_composing_objects.s2_instance_confinement;

import de.holhar.java_dev_kb.training.concurrency.ch04_composing_objects.util.Widget;
import de.holhar.java_dev_kb.training.concurrency.utils.GuardedBy;

/**
 * Guarding state with a private lock.
 */
public class PrivateLock {

    private final Object myLock = new Object();

    @GuardedBy("myLock")
    Widget widget;

    void something() {
        synchronized (myLock) {
            // Access or modify the state of widget
        }
    }
}
