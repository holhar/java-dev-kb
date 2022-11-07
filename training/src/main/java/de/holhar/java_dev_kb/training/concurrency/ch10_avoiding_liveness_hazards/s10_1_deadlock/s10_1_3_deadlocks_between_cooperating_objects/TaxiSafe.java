package de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.s10_1_deadlock.s10_1_3_deadlocks_between_cooperating_objects;

import de.holhar.java_dev_kb.training.concurrency.ch04_composing_objects.s4_3_delegating_thread_safety.s4_3_1_example_vehicle_tracker_using_delegation.Point;
import de.holhar.java_dev_kb.training.concurrency.utils.GuardedBy;
import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;

/**
 * Using open calls to avoiding deadlock between cooperating objects.
 */
@ThreadSafe
public class TaxiSafe {

  @GuardedBy("this")
  private Point location, destination;

  private final DispatcherSafe dispatcher;

  public TaxiSafe(DispatcherSafe dispatcher) {
    this.dispatcher = dispatcher;
  }

  public Point getLocation() {
    synchronized (this) {
      return location;
    }
  }

  public void setLocation(Point location) {
    boolean reachedDestination;
    synchronized (this) {
      this.location = location;
      reachedDestination = location.equals(destination);
    }
    if (reachedDestination) {
      dispatcher.notifyAvailable(this);
    }
  }
}
