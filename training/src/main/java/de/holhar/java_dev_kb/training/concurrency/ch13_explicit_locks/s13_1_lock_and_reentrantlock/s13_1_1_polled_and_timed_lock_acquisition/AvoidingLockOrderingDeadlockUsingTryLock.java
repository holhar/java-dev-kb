package de.holhar.java_dev_kb.training.concurrency.ch13_explicit_locks.s13_1_lock_and_reentrantlock.s13_1_1_polled_and_timed_lock_acquisition;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

import de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.utils.DollarAmount;
import de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.utils.InsufficientFundsException;
import de.holhar.java_dev_kb.training.concurrency.ch13_explicit_locks.utils.Account;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Avoiding lock-ordering deadlock using TryLock.
 */
public class AvoidingLockOrderingDeadlockUsingTryLock {

  Random rnd = new Random();

  public boolean transferMoney(
      Account fromAcct,
      Account toAcct,
      DollarAmount amount,
      long timeout,
      TimeUnit unit
  ) throws InsufficientFundsException, InterruptedException {
    long fixedDelay = getFixedDelayComponentNanos(timeout, unit);
    long randMod = getRandomDelayComponentNanos(timeout, unit);
    long stopTime = System.nanoTime() + unit.toNanos(timeout);

    while (true) {
      if (fromAcct.lock.tryLock()) {
        try {
          if (toAcct.lock.tryLock()) {
            try {
              if (fromAcct.getBalance().getAmount().compareTo(amount.getAmount()) < 0) {
                throw new InsufficientFundsException();
              } else {
                fromAcct.debit(amount);
                toAcct.credit(amount);
                return true;
              }
            } finally {
              toAcct.lock.unlock();
            }
          }
        } finally {
          fromAcct.lock.unlock();
        }
      }

      if (System.nanoTime() > stopTime) {
        return false;
      }
      NANOSECONDS.sleep(fixedDelay + rnd.nextLong() % randMod);
    }
  }

  private long getFixedDelayComponentNanos(long timeout, TimeUnit unit) {
    return 0;
  }

  private long getRandomDelayComponentNanos(long timeout, TimeUnit unit) {
    return 0;
  }
}
