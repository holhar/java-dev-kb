package de.holhar.java_dev_kb.training.ocp8.ch06_exceptions_assertions.sec05_assertions;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * Assertions can help uncover ripple effects of changes.
 */
public class TestSeasons {
    public static void test(Seasons s) {
        switch (s) {
            case SPRING:
            case FALL:
                println("Shorter hours");
                break;
            case SUMMER:
                println("Longer hours");
                break;
            default:
                assert false : "Invalid season";
        }
    }
}
