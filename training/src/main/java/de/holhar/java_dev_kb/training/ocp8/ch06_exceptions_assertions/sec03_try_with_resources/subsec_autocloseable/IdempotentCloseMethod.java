package de.holhar.java_dev_kb.training.ocp8.ch06_exceptions_assertions.sec03_try_with_resources.subsec_autocloseable;

/**
 * Java strongly recommends that close() not actually throw Exception. It is better to throw a more specific
 * exception. Java also recommends to make the close() method idemptent
 */
public class IdempotentCloseMethod {
}

// Good implementation: specific exception and idempotent
class ExampleOne implements AutoCloseable {
    @Override
    public void close() throws IllegalStateException {
        throw new IllegalStateException("Cage door does not close");
    }
}

// Bad implementation: too generic exception
class ExampleTwo implements AutoCloseable {
    @Override
    public void close() throws Exception {
        throw new Exception("Cage door does not close");
    }
}

// Bad implementation: close changes state of the class
class ExampleThree implements AutoCloseable {
    static int COUNT = 0;

    @Override
    public void close() {
        COUNT++;
    }
}


