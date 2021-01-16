package de.holhar.java_dev_kb.training.effectivejava.item30;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeightTable {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeightTable.class);

    public static void main(String[] args) {
        double earthWeight = Double.parseDouble(args[0]);
        double mass = earthWeight / Planet.EARTH.surfaceGravity();
        for (Planet p : Planet.values())
            LOGGER.debug("Weight on {} is {}", p, p.surfaceWeight(mass));
    }
}
