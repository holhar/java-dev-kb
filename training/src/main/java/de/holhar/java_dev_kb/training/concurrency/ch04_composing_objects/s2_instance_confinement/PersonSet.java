package de.holhar.java_dev_kb.training.concurrency.ch04_composing_objects.s2_instance_confinement;

import de.holhar.java_dev_kb.training.concurrency.ch04_composing_objects.util.Person;
import de.holhar.java_dev_kb.training.concurrency.utils.GuardedBy;
import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * Using confinement to ensure thread safety.
 */
@ThreadSafe
public class PersonSet {

    @GuardedBy("this")
    private final Set<Person> mySet = new HashSet<>();

    public synchronized void addPerson(Person p) {
        mySet.add(p);
    }

    public synchronized boolean containsPerson(Person p) {
        return mySet.contains(p);
    }
}
