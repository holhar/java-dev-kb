package de.holhar.java_dev_kb.designpatterns.behavioral.chain_of_responsibility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dollar20Dispenser implements DispenseChain {

    private static final Logger logger = LoggerFactory.getLogger(Dollar20Dispenser.class);

    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void dispense(Currency currency) {
        if (currency.getAmount() >= 20) {
            int number = currency.getAmount() / 20;
            int remainder = currency.getAmount() % 20;
            logger.info("Dispensing '{}' 20$ notes", number);
            if (remainder != 0) {
                this.chain.dispense(new Currency(remainder));
            }
        } else {
            this.chain.dispense(currency);
        }
    }
}
