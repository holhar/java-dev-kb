package de.holhar.java_dev_kb.training.concurrency.utils;

/**
 * Section 5.5.2 FutureTask
 * Coercing an unchecked Throwable to a RuntimeException.
 */
public class ExceptionUtils {

    private ExceptionUtils() { }

    /**
     * If the Throwable is an Error, throw it; if it is a RuntimeException
     * return it, otherwise throw IllegalStateException.
     */
    public static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new IllegalStateException("Not unchecked", t);
        }
    }
}
