package de.holhar.java_dev_kb.designpatterns.behavioral.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TvStopState implements State {

    private static final Logger logger = LoggerFactory.getLogger(TvStopState.class);

    @Override
    public void doAction() {
        logger.info("TV is turned OFF");
    }
}
