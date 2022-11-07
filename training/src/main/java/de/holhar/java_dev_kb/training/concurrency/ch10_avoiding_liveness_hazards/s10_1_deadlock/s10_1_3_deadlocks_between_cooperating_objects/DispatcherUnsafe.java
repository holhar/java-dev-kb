package de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.s10_1_deadlock.s10_1_3_deadlocks_between_cooperating_objects;

import de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.utils.Image;
import de.holhar.java_dev_kb.training.concurrency.utils.GuardedBy;
import java.util.HashSet;
import java.util.Set;

/**
 * Lock-ordering deadlock between cooperating objects.
 * DON'T DO THIS.
 *
 * Warning: deadlock-prone!
 */
public class DispatcherUnsafe {

  @GuardedBy("this")
  private final Set<TaxiUnsafe> taxis;

  @GuardedBy("this")
  private final Set<TaxiUnsafe> availableTaxis;

  public DispatcherUnsafe() {
    this.taxis = new HashSet<>();
    this.availableTaxis = new HashSet<>();
  }

  public synchronized void notifyAvailable(TaxiUnsafe taxi) {
      availableTaxis.add(taxi);
  }

  public synchronized Image getImage() {
    Image image = new Image();
    for (TaxiUnsafe taxi : taxis) {
      image.drawMarker(taxi.getLocation());
    }
    return image;
  }
}
