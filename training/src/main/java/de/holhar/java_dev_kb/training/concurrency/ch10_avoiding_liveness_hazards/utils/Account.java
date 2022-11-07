package de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.utils;

import java.math.BigDecimal;

public class Account {

  private DollarAmount balance;

  public Account() {
    this.balance = new DollarAmount(new BigDecimal("5000"));
  }

  public void setBalance(DollarAmount balance) {
    this.balance = balance;
  }

  public DollarAmount getBalance() {
    return balance;
  }

  public void debit(DollarAmount dollarAmount) {
    // ...
  }

  public void credit(DollarAmount dollarAmount) {
    // ...
  }
}
