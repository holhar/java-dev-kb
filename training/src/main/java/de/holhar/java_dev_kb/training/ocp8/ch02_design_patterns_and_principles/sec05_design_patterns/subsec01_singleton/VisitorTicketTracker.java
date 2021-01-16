package de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec05_design_patterns.subsec01_singleton;

/**
 * Applying lazy instantiation to singletons - only create an instance when it's requested for the first time
 * |-> Decrease memory usage
 */
public class VisitorTicketTracker {

    private static VisitorTicketTracker instance;

    private VisitorTicketTracker() {
    }

    /*
     * The 'synchronized' implementation of 'getInstance()', while correctly preventing multiple singleton objects from
     * being created, has the problem that every single call to this method will require synchronization. In practice,
     * this can be costly and can impact performance. Synchronization is only needed the first time that the object is
     * created.
     *
     * The solution is to use 'double-checked locking'
     */
    public static synchronized VisitorTicketTracker getInstance() {
        if (instance == null) {
            instance = new VisitorTicketTracker(); // NOT THREAD-SAFE whithout the 'synchronized' keyword
        }
        return instance;
    }

    // Data access methods
    // ...
}
