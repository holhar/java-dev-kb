package de.holhar.java_dev_kb.designpatterns.behavioral.chain_of_responsibility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dollar50Dispenser implements DispenseChain {

    private static final Logger logger = LoggerFactory.getLogger(Dollar50Dispenser.class);

    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void dispense(Currency currency) {
        if (currency.getAmount() >= 50) {
            int number = currency.getAmount() / 50;
            int remainder = currency.getAmount() % 50;
            logger.info("Dispensing '{}' 50$ notes", number);
            if (remainder != 0) {
                this.chain.dispense(new Currency(remainder));
            }
        } else {
            this.chain.dispense(currency);
        }
    }
}
