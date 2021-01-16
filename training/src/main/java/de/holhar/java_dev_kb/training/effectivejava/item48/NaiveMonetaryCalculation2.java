package de.holhar.java_dev_kb.training.effectivejava.item48;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class NaiveMonetaryCalculation2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(NaiveMonetaryCalculation2.class);

    // Broken - uses floating point for monetary calculation!
    public static void main(String[] args) {
        double funds = 1.00;
        int itemsBought = 0;
        for (double price = .10; funds >= price; price += .10) {
            funds -= price;
            itemsBought++;
        }
        LOGGER.debug("{} items bought.", itemsBought);
        LOGGER.debug("Change: ${}", funds);
    }
}
