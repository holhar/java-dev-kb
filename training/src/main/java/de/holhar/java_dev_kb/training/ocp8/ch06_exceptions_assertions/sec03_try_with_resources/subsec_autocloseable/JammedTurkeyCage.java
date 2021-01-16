package de.holhar.java_dev_kb.training.ocp8.ch06_exceptions_assertions.sec03_try_with_resources.subsec_autocloseable;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * Suppressed exceptions example
 */
public class JammedTurkeyCage implements AutoCloseable {

    public static void main(String[] args) {
        exampleOne();
        exampleTwo();
        exampleThree();
        exampleFour();
    }

    // Basic mechanism of primary and suppressed exceptions
    private static void exampleOne() {
        try (JammedTurkeyCage t = new JammedTurkeyCage()) {
            // This is the PRIMARY exception:
            throw new IllegalStateException("Turkeys ran off");

            // This catch block looks for matches of the PRIMARY exception
        } catch (IllegalStateException e) {
            println("caught: " + e.getMessage());
            for (Throwable t : e.getSuppressed()) {
                println(t.getMessage());
            }
        }
    }

    // Passing on primary exceptions to the caller
    private static void exampleTwo() {
        try (JammedTurkeyCage t = new JammedTurkeyCage()) {
            //throw new RuntimeException("Turkeys ran off");

            // The PRIMARY exception - the RuntimeException - gets not caught here; the main method will
            // take care of the output
        } catch (IllegalStateException e) {
            println("caught: " + e.getMessage());
        }
    }

    // Primary and suppressed exceptions thrown during close() only
    private static void exampleThree() {
        try (JammedTurkeyCage t1 = new JammedTurkeyCage();
             JammedTurkeyCage t2 = new JammedTurkeyCage()) {
            println("Turkeys entered cage");
        } catch (IllegalStateException e) {
            // t2 throws the first exception during close and becomes the primary exception
            println("Caught: " + e.getMessage());
            // t1 throws another exception, which is added to the suppressed exceptions
            for (Throwable t : e.getSuppressed()) {
                println(t.getMessage());
            }
        }
    }

    /* Suppressed exceptions apply only to exceptions thrown in the try clause; the following example does
     * not throw a suppressed exception:
     */
    private static void exampleFour() {
        try (JammedTurkeyCage t = new JammedTurkeyCage()) {
            // The primary exceptions gets thrown; then, during close(), a suppressed exception gets added
            throw new IllegalStateException("Turkeys ran off");
        } finally {
            // Another exceptions gets thrown here and the previous exception is lost...
            // This has always been and continues to be bad programming practice - WE DON'T WANT TO LOSE EXCEPTIONS!
            throw new RuntimeException("...and we couldn't find them");
        }
    }

    // Gets called in implicit finally clause after try block
    @Override
    public void close() throws IllegalStateException {
        // Another exception, which is added to the suppressed exceptions
        throw new IllegalStateException("Cage door does not close");
    }
}
