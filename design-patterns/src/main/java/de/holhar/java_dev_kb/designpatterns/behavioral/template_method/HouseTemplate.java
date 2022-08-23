package de.holhar.java_dev_kb.designpatterns.behavioral.template_method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class HouseTemplate {

    private static final Logger logger = LoggerFactory.getLogger(HouseTemplate.class);

    // Template method, final, so subclasses cannot override
    public final void buildHouse() {
        buildFoundation();
        buildPillars();
        buildWalls();
        buildWindows();
        logger.info("House is built.");
    }

    // Methods to be implemented by subclasses
    public abstract void buildWalls();
    public abstract void buildPillars();

    // Default implementation
    private void buildFoundation() {
        logger.info("Building foundation with cement, iron, rods, and sand");
    }

    private void buildWindows() {
        logger.info("Building glass windows");
    }
}
