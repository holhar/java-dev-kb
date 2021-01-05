package de.holhar.java_dev_kb.designpatterns.structural.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConcreteComponent extends Component {

    private static final Logger LOGGER = LogManager.getLogger(ConcreteComponent.class);

    @Override
    public void makeHouse() {
        LOGGER.debug("Original is complete. It is closed for modification.");
    }
}
