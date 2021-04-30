package de.holhar.java_dev_kb.training.concurrency.ch04_composing_objects.s4_add_functionality_to_thread_safe_classes;

import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementing put-if-absent with client-side locking.
 */
@ThreadSafe
public class ListHelperSafe<E> {

    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    // ...

    public boolean putIfAbsent(E x) {
        synchronized (list) {
            boolean absent = !list.contains(x);
            if (absent) {
                list.add(x);
            }
            return absent;
        }
    }
}
