package de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.utils;

import java.math.BigDecimal;

public class DollarAmount implements Comparable<DollarAmount> {

  private final BigDecimal amount;

  public DollarAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  @Override
  public int compareTo(DollarAmount dollarAmount) {
    return this.amount.intValue() - dollarAmount.amount.intValue();
  }
}
