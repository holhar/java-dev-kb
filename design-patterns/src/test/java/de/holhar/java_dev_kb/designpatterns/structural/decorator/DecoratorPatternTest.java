package de.holhar.java_dev_kb.designpatterns.structural.decorator;

import org.junit.jupiter.api.Test;

class DecoratorPatternTest {

    @Test
    void decoratorPatternTest() {
        SportsCar sportsCar = new SportsCar(new BasicCar());
        sportsCar.assemble();

        SportsCar luxurySportsCar = new SportsCar(new LuxuryCar(new BasicCar()));
        luxurySportsCar.assemble();
    }

}
