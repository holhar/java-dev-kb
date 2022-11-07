package de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.s10_1_deadlock.s10_1_3_deadlocks_between_cooperating_objects;

import de.holhar.java_dev_kb.training.concurrency.ch04_composing_objects.s4_3_delegating_thread_safety.s4_3_1_example_vehicle_tracker_using_delegation.Point;
import de.holhar.java_dev_kb.training.concurrency.utils.GuardedBy;

/**
 * Lock-ordering deadlock between cooperating objects.
 * DON'T DO THIS.
 *
 * Warning: deadlock-prone!
 */
public class TaxiUnsafe {

  @GuardedBy("this")
  private Point location, destination;

  private final DispatcherUnsafe dispatcher;

  public TaxiUnsafe(DispatcherUnsafe dispatcher) {
    this.dispatcher = dispatcher;
  }

  public synchronized Point getLocation() {
    return location;
  }

  public synchronized void setLocation(Point location) {
    this.location = location;
    if (location.equals(destination)) {
      dispatcher.notifyAvailable(this);
    }
  }
}
