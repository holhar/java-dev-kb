package de.holhar.java_dev_kb.training.effectivejava.item35;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Program to process marker annotations
 */
public class RunTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(RunTests.class);

    public static void main(String[] args) throws Exception {
        int tests = 0;
        int passed = 0;
        Class testClass = Class.forName(args[0]);

        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                tests++;
                try {
                    m.invoke(null);
                    passed++;
                } catch (InvocationTargetException wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    LOGGER.debug("{} failed: {}", m, exc);
                } catch (Exception exc) {
                    LOGGER.debug("INVALID @Test: {}", m);
                }
            }
        }
        LOGGER.debug("Passed: {}, Failed: {}", passed, tests - passed);
    }
}
