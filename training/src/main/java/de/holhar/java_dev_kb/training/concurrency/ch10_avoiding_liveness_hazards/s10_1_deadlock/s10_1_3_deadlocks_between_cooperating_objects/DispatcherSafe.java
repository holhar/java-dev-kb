package de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.s10_1_deadlock.s10_1_3_deadlocks_between_cooperating_objects;

import de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.utils.Image;
import de.holhar.java_dev_kb.training.concurrency.utils.GuardedBy;
import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;
import java.util.HashSet;
import java.util.Set;

/**
 * Using open calls to avoiding deadlock between cooperating objects.
 */
@ThreadSafe
public class DispatcherSafe {

  @GuardedBy("this")
  private final Set<TaxiSafe> taxis;

  @GuardedBy("this")
  private final Set<TaxiSafe> availableTaxis;

  public DispatcherSafe() {
    this.taxis = new HashSet<>();
    this.availableTaxis = new HashSet<>();
  }

  public void notifyAvailable(TaxiSafe taxi) {
    synchronized (this) {
      availableTaxis.add(taxi);
    }
  }

  public Image getImage() {
    Set<TaxiSafe> copy;
    synchronized (this) {
      copy = new HashSet<>(taxis);
    }
    Image image = new Image();
    for (TaxiSafe taxi : copy) {
      image.drawMarker(taxi.getLocation());
    }
    return image;
  }
}
