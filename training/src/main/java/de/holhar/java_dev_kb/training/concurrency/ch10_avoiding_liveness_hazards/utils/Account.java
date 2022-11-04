package de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.utils;

public class Account {

  private final DollarAmount balance;

  public Account(DollarAmount balance) {
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
