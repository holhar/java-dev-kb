package de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.s10_1_deadlock.s10_1_2_dynamic_lock_order_deadlocks;

import de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.utils.Account;
import de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.utils.DollarAmount;
import de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.utils.InsufficientFundsException;

/**
 * Inducing a lock ordering to avoid deadlock.
 */
public class InducingLockOrdering {

  private static final Object tielock = new Object();

  public void transferMoney(final Account fromAcct, final Account toAcct,
      final DollarAmount amount) throws InsufficientFundsException {

    class Helper {
      public void transfer() throws InsufficientFundsException {
        if (fromAcct.getBalance().compareTo(amount) < 0) {
          throw new InsufficientFundsException();
        } else {
          fromAcct.debit(amount);
          toAcct.credit(amount);
        }
      }
    }

    int fromHash = System.identityHashCode(fromAcct);
    int toHash = System.identityHashCode(toAcct);

    if (fromHash < toHash) {
      synchronized (fromAcct) {
        synchronized (toAcct) {
          new Helper().transfer();
        }
      }
    } else if (toHash < fromHash) {
      synchronized (toAcct) {
        synchronized (fromAcct) {
          new Helper().transfer();
        }
      }
    } else {
      synchronized (tielock) {
        synchronized (fromAcct) {
          synchronized (toAcct) {
            new Helper().transfer();
          }
        }
      }
    }
  }
}
