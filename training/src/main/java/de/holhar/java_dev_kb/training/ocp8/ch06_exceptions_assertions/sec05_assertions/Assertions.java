package de.holhar.java_dev_kb.training.ocp8.ch06_exceptions_assertions.sec05_assertions;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * @author hhs@dasburo.com
 * <p>
 * General syntax:
 * - activate assertions only for specific packages (for all classes below 'demos'):
 * $ java -ea:de.holhar.demos... my.programs.Main
 * <p>
 * - activate assertions only for a specific class:
 * $ java -ea:de.holhar.demos.TestColors my.programs.Main
 * <p>
 * - disable assertions for specific packages or classes:
 * $ java -ea:de.holhar.demos... -da:.de.holhar.demos.TestColors my.programs.Main
 */
public class Assertions {

    /*
     * Program fails if run with '$ java -ea ...'
     */
    public static void main(String[] args) {
        int numGuests = -5;
        assert numGuests > 0 : "numGuests is not allowed to be negative";
        println(numGuests);
    }
}
