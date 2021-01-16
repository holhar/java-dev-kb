package de.holhar.java_dev_kb.training.effectivejava.item35;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Program to process annotation type with a parameter (or array parameter)
 */
public class RunTests2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(RunTests2.class);

    public static void main(String[] args) throws Exception {
        int tests = 0;
        int passed = 0;
        Class testClass = Class.forName(args[0]);

        for (Method m : testClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(ExceptionTest.class)) {
                tests++;
                try {
                    m.invoke(null);
                    LOGGER.debug("Test {} failed: no exception", m);
                } catch (InvocationTargetException wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    Class<? extends Exception>[] excTypes =
                            m.getAnnotation(ExceptionTest.class).value();
                    int oldPassed = passed;

                    for (Class<? extends Exception> excType : excTypes) {
                        if (excType.isInstance(exc)) {
                            passed++;
                            break;
                        }
                    }

                    if (passed == oldPassed) {
                        LOGGER.debug("Test {} failed: {}", m, exc);
                    }
                }
            }
        }
        LOGGER.debug("Passed: {}, Failed: {}", passed, tests - passed);
    }
}
