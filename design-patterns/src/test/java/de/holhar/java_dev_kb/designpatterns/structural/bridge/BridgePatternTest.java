package de.holhar.java_dev_kb.designpatterns.structural.bridge;

import org.junit.jupiter.api.Test;

class BridgePatternTest {

    @Test
    void bridgePattern() {
        Triangle triangle = new Triangle(new RedColor());
        triangle.applyColor();

        Pentagon pentagon = new Pentagon(new GreenColor());
        pentagon.applyColor();
    }

}
