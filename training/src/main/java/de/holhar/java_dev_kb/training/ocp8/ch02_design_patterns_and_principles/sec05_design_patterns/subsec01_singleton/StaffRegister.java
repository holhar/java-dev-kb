package de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec05_design_patterns.subsec01_singleton;

/**
 * Singleton using a static initialization block to perform additional steps for the object construction
 */
public class StaffRegister {

    private static final StaffRegister instance;

    static {
        instance = new StaffRegister();
        // perform additional steps
    }

    private StaffRegister() {
    }

    public static StaffRegister getInstance() {
        return instance;
    }

    // Data access methods
    // ...
}
