package de.holhar.java_dev_kb.designpatterns.behavioral.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaypalStrategy implements PaymentStrategy {

    private static final Logger logger = LoggerFactory.getLogger(PaypalStrategy.class);

    private final String emailId;
    private final String password;

    public PaypalStrategy(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
    }

    @Override
    public void pay(int amount) {
        logger.info("'{}' paid using PayPal.", amount);
    }
}
