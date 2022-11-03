package de.holhar.java_dev_kb.training.concurrency.ch04_composing_objects.s4_3_delegating_thread_safety.s4_3_1_example_vehicle_tracker_using_delegation;

import de.holhar.java_dev_kb.training.concurrency.utils.Immutable;

/**
 * Immutable Point class used by DelegatingVehicleTracker.
 */
@Immutable
public class Point {

    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
