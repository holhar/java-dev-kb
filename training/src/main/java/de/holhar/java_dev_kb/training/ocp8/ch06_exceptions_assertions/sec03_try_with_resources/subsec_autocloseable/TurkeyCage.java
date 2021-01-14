package de.holhar.java_dev_kb.training.ocp8.ch06_exceptions_assertions.sec03_try_with_resources.subsec_autocloseable;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * @author hhs@dasburo.com
 */
public class TurkeyCage implements AutoCloseable {

    public static void main(String[] args) {

        try (TurkeyCage t = new TurkeyCage()) {
            println("Put turkeys in");
        }
    }

    // It's not mandatory for the close method to throw an Exception
    public void close() {
        println("Close gate");
    }
}
