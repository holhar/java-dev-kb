package de.holhar.java_dev_kb.training.effectivejava.item33;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhaseClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(PhaseClient.class);

    public static void main(String[] args) {
        LOGGER.debug("What's the transition from a gas to a liquid?:");
        LOGGER.debug("{}", Phase.Transition.from(Phase.GAS, Phase.LIQUID));
    }
}
