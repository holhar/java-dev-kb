package de.holhar.java_dev_kb.designpatterns.behavioral.template_method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HousingClient {

    private static final Logger logger = LoggerFactory.getLogger(HousingClient.class);

    public static void main(String[] args) {
        WoodenHouse woodenHouse = new WoodenHouse();
        woodenHouse.buildHouse();

        logger.info("*******************");

        GlassHouse glassHouse = new GlassHouse();
        glassHouse.buildHouse();
    }
}
