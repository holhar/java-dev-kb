package de.holhar.java_dev_kb.training.concurrency.ch13_explicit_locks.utils;

import de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.utils.DollarAmount;
import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

  public final Lock lock = new ReentrantLock();

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
