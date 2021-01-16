package de.holhar.java_dev_kb.training.effectivejava.item76;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

class Attacker {

    private static final Logger LOGGER = LoggerFactory.getLogger(Attacker.class);

    public static void main(String[] args) {
        MutablePeriod mp = new MutablePeriod();
        Period p = mp.period;
        Date pEnd = mp.end;

        // Let's turn back the clock
        pEnd.setYear(78);
        LOGGER.debug("{}", p);

        // Bring back the 60s!
        pEnd.setYear(69);
        LOGGER.debug("{}", p);
    }
}