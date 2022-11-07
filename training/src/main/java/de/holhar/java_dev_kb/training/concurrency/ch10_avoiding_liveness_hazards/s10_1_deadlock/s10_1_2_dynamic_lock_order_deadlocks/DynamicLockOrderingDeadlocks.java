package de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.s10_1_deadlock.s10_1_2_dynamic_lock_order_deadlocks;

import de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.utils.Account;
import de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.utils.DollarAmount;
import de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.utils.InsufficientFundsException;

/**
 * Dynamic lock-ordering deadlocks.
 * DON'T DO THIS.
 *
 */
public class DynamicLockOrderingDeadlocks {

  private DynamicLockOrderingDeadlocks() { }

  /*
   * Warning: deadlock-prone!
   */
  public static void transferMoney(Account fromAccount, Account toAccount, DollarAmount amount)
      throws InsufficientFundsException {

    synchronized (fromAccount) {
      synchronized (toAccount) {
        if (fromAccount.getBalance().compareTo(amount) < 0) {
          throw new InsufficientFundsException();
        } else {
          fromAccount.debit(amount);
          toAccount.credit(amount);
        }
      }
    }
  }
}
