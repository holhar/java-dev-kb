package de.holhar.java_dev_kb.training.concurrency.ch10_avoiding_liveness_hazards.utils;

public class InsufficientFundsException extends Exception {

  public InsufficientFundsException(String message) {
    super(message);
  }
}
