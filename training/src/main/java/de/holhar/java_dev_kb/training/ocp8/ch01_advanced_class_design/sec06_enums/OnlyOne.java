package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec06_enums;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public enum OnlyOne {

    ONCE(true);

    private OnlyOne(boolean b) {
        println("constructing");
    }

    // The first time we ask for any of the enum values, Java constructs all of the enum values
    // After that, Java returns the already-constructed enum values
    public static void main(String[] args) {
        OnlyOne firstCall = OnlyOne.ONCE;   // prints constructing
        OnlyOne secondCall = OnlyOne.ONCE;  // doesn't print anything
    }
}
