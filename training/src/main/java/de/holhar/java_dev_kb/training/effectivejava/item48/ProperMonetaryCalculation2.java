package de.holhar.java_dev_kb.training.effectivejava.item48;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ProperMonetaryCalculation2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProperMonetaryCalculation2.class);

    public static void main(String[] args) {
        int itemsBought = 0;
        int funds = 100;
        for (int price = 10; funds >= price; price += 10) {
            itemsBought++;
            funds -= price;
        }
        LOGGER.debug("{} items bought.", itemsBought);
        LOGGER.debug("Money left over: ${} cents", funds);
    }
}
