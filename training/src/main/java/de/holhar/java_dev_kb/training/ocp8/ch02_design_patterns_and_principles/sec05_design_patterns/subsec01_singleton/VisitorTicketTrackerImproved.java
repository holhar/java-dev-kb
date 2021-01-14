package de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec05_design_patterns.subsec01_singleton;

/**
 * @author hhs@dasburo.com
 *
 * A version of this singleton class using 'double-checked locking'
 */
public class VisitorTicketTrackerImproved {

    /*
     * The 'volatile' keyword prevents compiler optimizations.
     */
    private static volatile VisitorTicketTrackerImproved instance;

    private VisitorTicketTrackerImproved() {
    }

    public static VisitorTicketTrackerImproved getInstance() {
        if (instance == null) {
            synchronized (VisitorTicketTrackerImproved.class) {
                if (instance == null) {
                    instance = new VisitorTicketTrackerImproved();
                }
            }
        }
        return instance;
    }
}
