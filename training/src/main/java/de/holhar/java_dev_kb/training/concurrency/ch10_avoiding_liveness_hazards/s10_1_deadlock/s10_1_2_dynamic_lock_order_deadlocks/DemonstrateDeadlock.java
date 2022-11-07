package de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.s10_1_deadlock.s10_1_2_dynamic_lock_order_deadlocks;

import de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.utils.Account;
import de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.utils.DollarAmount;
import de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.utils.InsufficientFundsException;
import java.math.BigDecimal;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Driver loop that induces deadlock under typical conditions.
 */
public class DemonstrateDeadlock {

  private static final Logger logger = LoggerFactory.getLogger(DemonstrateDeadlock.class);

  private static final int NUM_THREADS = 20;
  private static final int NUM_ACCOUNTS = 5;
  private static final int NUM_ITERATIONS = 1_000_000;

  public static void main(String[] args) {
    final Random random = new Random();
    final Account[] accounts = new Account[NUM_ACCOUNTS];

    logger.info("start main");

    for (int i = 0; i < accounts.length; i++) {
      accounts[i] = new Account();
    }

    logger.info("accounts initialized");

    class TransferThread extends Thread {

      @Override
       public void run() {
         for (int i = 0; i < NUM_ITERATIONS; i++) {
           if (i % 10 == 0) {
             logger.info("transfer loop no. {} started", i);
           }
           int fromAcct = random.nextInt(NUM_ACCOUNTS);
           int toAcct = random.nextInt(NUM_ACCOUNTS);
           DollarAmount amount = new DollarAmount(new BigDecimal(random.nextInt(1000)));
           try {
             DynamicLockOrderingDeadlocks.transferMoney(accounts[fromAcct], accounts[toAcct], amount);
           } catch (InsufficientFundsException e) {
             // ignore
           }
         }
       }
    }

    for (int i = 0; i < NUM_THREADS; i++) {
      logger.info("start transfer thread {}", i);
      new TransferThread().start();
    }
  }
}
