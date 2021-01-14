package de.holhar.java_dev_kb.training.ocp8.ch06_exceptions_assertions.sec01_custom_exceptions;

/**
 * @author hhs@dasburo.com
 */
public class Dolphin {
    public void swim() throws CannotSwim2Exception {
        // logic here
    }
}

class CannotSwim2Exception extends Exception {
}

class DangerInTheWater extends RuntimeException {
}

class SharkInTheWaterException extends DangerInTheWater {
}
