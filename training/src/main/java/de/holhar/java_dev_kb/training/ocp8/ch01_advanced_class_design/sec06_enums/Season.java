package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec06_enums;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public enum Season {
    // No semicolon for simple enumeration
    // WINTER, SPRING, SUMMER, FALL

    /*
     * Using constructors, fields, and methods
     * =======================================
     */

    // Now a semicolon is necessary, as it's no simple enumeration anymore
    WINTER("Low"), SPRING("Medium"), SUMMER("High"), FALL("Low");

    // The defined parameter we see above for each enum value
    private String expectedVisitors;

    // The constructor is private - it's only called from within the enum
    private Season(String expectedVisitors) {
        this.expectedVisitors = expectedVisitors;
    }

    // The method is defined in a regular way
    public void printExpectedVisitors() {
        println(expectedVisitors);
    }
}
