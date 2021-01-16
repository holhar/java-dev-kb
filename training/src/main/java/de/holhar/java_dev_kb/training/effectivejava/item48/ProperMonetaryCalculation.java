package de.holhar.java_dev_kb.training.effectivejava.item48;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class ProperMonetaryCalculation {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProperMonetaryCalculation.class);

    public static void main(String[] args) {
        final BigDecimal TEN_CENTS = new BigDecimal(".10");

        int itemsBought = 0;
        BigDecimal funds = new BigDecimal("1.00");
        for (BigDecimal price = TEN_CENTS;
             funds.compareTo(price) >= 0;
             price = price.add(TEN_CENTS)) {
            itemsBought++;
            funds = funds.subtract(price);
        }
        LOGGER.debug("{} items bought.", itemsBought);
        LOGGER.debug("Money left over: ${}", funds);
    }
}
