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

  /*
   * Warning: deadlock-prone!
   */
  public void transferMoney(Account fromAccount, Account toAccount, DollarAmount dollarAmount)
      throws InsufficientFundsException {

    synchronized (fromAccount) {
      synchronized (toAccount) {
        if (fromAccount.getBalance().compareTo(dollarAmount) < 0) {
          throw new InsufficientFundsException("Not enough balance!");
        } else {
          fromAccount.debit(dollarAmount);
          toAccount.credit(dollarAmount);
        }
      }
    }
  }
}
