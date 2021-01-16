package de.holhar.java_dev_kb.training.ocp8.ch06_exceptions_assertions.sec03_try_with_resources.subsec_autocloseable;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class StuckTurkeyCage implements AutoCloseable {

    public static void main(String[] args) {
        // DOES NOT COMPILE, if Exception is not being handled
        try (StuckTurkeyCage t = new StuckTurkeyCage()) {
            println("Put turkeys in");
        } catch (Exception e) {
            // Handle Exception...
        }
    }

    public void close() throws Exception {
        throw new Exception("Cage door does not cloes");
    }
}
