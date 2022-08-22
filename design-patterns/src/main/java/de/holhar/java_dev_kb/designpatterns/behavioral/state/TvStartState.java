package de.holhar.java_dev_kb.designpatterns.behavioral.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TvStartState implements State {

    private static final Logger logger = LoggerFactory.getLogger(TvStartState.class);

    @Override
    public void doAction() {
        logger.info("TV is turned ON");
    }
}
