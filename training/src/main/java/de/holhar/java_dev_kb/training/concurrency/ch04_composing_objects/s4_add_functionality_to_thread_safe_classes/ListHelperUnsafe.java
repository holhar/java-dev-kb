package de.holhar.java_dev_kb.training.concurrency.ch04_composing_objects.s4_add_functionality_to_thread_safe_classes;

import de.holhar.java_dev_kb.training.concurrency.utils.NotThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Non-thread-safe attempt to implement put-if-absent.
 * Don't do this.
 */
@NotThreadSafe
public class ListHelperUnsafe<E> {

    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    // ...

    /*
     * ListHelper and List instances are using different locks!
     */
    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);
        if (absent) {
            list.add(x);
        }
        return absent;
    }
}
