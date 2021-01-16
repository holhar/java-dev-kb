package de.holhar.java_dev_kb.training.ocp8.ch06_exceptions_assertions.sec03_try_with_resources.subsec_autocloseable;

public class Turkey {

    public static void main(String[] args) {
        // DOES NOT COMPILE as long as Turkey does not implement AutoCloseable
        // try (Turkey t = new Turkey()) { }
    }
}
