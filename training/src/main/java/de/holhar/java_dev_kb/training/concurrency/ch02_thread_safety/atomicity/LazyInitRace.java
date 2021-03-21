package de.holhar.java_dev_kb.training.concurrency.ch02_thread_safety.atomicity;

import de.holhar.java_dev_kb.training.concurrency.utils.NotThreadSafe;

/**
 * Race condition in lazy initialization. Don't do this.
 */
@NotThreadSafe
public class LazyInitRace {

    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
        // instance reference might still be null during instantiation of ExpensiveObject
        if (instance == null) {
            instance = new ExpensiveObject();
        }
        return instance;
    }

    public static class ExpensiveObject {}
}
