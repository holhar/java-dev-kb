package de.holhar.java_dev_kb.training.concurrency.ch04_composing_objects.s4_4_add_functionality_to_thread_safe_classes;

import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;

import java.util.Vector;

/**
 * Extending Vector to have a put-if-absent method.
 */
@ThreadSafe
public class BetterVector<E> extends Vector<E> {

    public synchronized  boolean putIfAbsent(E x) {
        boolean absent = !contains(x);
        if (absent) {
            add(x);
        }
        return absent;
    }
}
