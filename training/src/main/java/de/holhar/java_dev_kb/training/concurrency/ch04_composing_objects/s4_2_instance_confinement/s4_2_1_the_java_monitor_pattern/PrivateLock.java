package de.holhar.java_dev_kb.training.concurrency.ch04_composing_objects.s4_2_instance_confinement.s4_2_1_the_java_monitor_pattern;

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
