package de.holhar.java_dev_kb.training.concurrency.ch12_testing_concurrent_programs.s12_1_testing_for_correctness.s12_1_6_generating_interleavings;

import de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.utils.Account;
import de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.utils.DollarAmount;
import java.math.BigDecimal;
import java.util.Random;

/**
 * Using Thread.yield to generate more interleavings.
 */
public class GenerateInterleavings {

  private static final int THRESHOLD = 500;

  private final Random random = new Random();

  public synchronized void transferCredits(Account from, Account to, DollarAmount amount) {
    BigDecimal newFromAmount = from.getBalance().getAmount().subtract(amount.getAmount());
    from.setBalance(new DollarAmount(newFromAmount));
    if (random.nextInt(1000) > THRESHOLD) {
      Thread.yield();
    }
    BigDecimal newToAmount = to.getBalance().getAmount().add(amount.getAmount());
    to.setBalance(new DollarAmount(newToAmount));
  }
}
