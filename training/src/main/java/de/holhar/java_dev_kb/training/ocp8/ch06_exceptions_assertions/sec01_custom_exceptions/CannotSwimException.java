package de.holhar.java_dev_kb.training.ocp8.ch06_exceptions_assertions.sec01_custom_exceptions;

/**
 * @author hhs@dasburo.com
 * <p>
 * Common exception constructors.
 */
public class CannotSwimException extends Exception {

    public CannotSwimException() {
        super();
    }

    public CannotSwimException(Exception e) {
        super(e);
    }

    public CannotSwimException(String message) {
        super(message);
    }
}
