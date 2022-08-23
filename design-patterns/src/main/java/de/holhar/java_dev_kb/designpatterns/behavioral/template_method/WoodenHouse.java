package de.holhar.java_dev_kb.designpatterns.behavioral.template_method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WoodenHouse extends HouseTemplate {

    private static final Logger logger = LoggerFactory.getLogger(WoodenHouse.class);

    @Override
    public void buildWalls() {
        logger.info("Building wooden walls");
    }

    @Override
    public void buildPillars() {
        logger.info("Building pillars with wood coating");
    }
}
