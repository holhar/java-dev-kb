package de.holhar.java_dev_kb.designpatterns.behavioral.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreditCardStrategy implements PaymentStrategy {

    private static final Logger logger = LoggerFactory.getLogger(CreditCardStrategy.class);

    private final String name;
    private final String cardNumber;
    private final String cvv;
    private final String dateOfExpiry;

    public CreditCardStrategy(String name, String cardNumber, String cvv, String dateOfExpiry) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.dateOfExpiry = dateOfExpiry;
    }

    @Override
    public void pay(int amount) {
        logger.info("'{}' paid with credit card", amount);
    }
}
