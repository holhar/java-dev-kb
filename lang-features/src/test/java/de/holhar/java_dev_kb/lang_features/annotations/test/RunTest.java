package de.holhar.java_dev_kb.lang_features.annotations.test;

import de.holhar.java_dev_kb.lang_features.annotations.test.core.Test;
import de.holhar.java_dev_kb.lang_features.annotations.test.core.TesterInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RunTest {

    private static final Logger LOGGER = LogManager.getLogger(RunTest.class);

    public static void main(String[] args) throws Exception {

        LOGGER.info("Test annotation processing");

        int passed = 0;
        int failed = 0;
        int ignore = 0;
        int count = 0;

        Class clazz = Class.forName("de.holhar.annotations.test.TestExample");
        Constructor<de.holhar.java_dev_kb.lang_features.annotations.test.TestExample> constructor = clazz.getConstructor((Class[]) null);

        if (clazz.isAnnotationPresent(TesterInfo.class)) {
            var annotation = clazz.getAnnotation(TesterInfo.class);
            var testerInfo = (TesterInfo) annotation;

            LOGGER.info("\nPriority: {}", testerInfo.priority());
            LOGGER.info("\nCreatedBy: {}", testerInfo.createdBy());
            LOGGER.info("\nTags: {}\n", String.join(",", testerInfo.tags()));
        }

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                var annotation = method.getAnnotation(Test.class);
                var test = (Test) annotation;

                if (test.enabled()) {
                    try {
                        method.invoke(constructor.newInstance((Object[]) null));
                        LOGGER.info("{} - Test '{}' - passed\n", ++count, method.getName());
                        passed++;
                    } catch (Throwable ex) {
                        LOGGER.info("{} - Test '{}' - failed: {}\n", ++count, method.getName(), ex.getCause());
                        failed++;
                    }
                } else {
                    LOGGER.info("{} - Test '{}' - ignored\n", ++count, method.getName());
                    ignore++;
                }
            }
        }
        LOGGER.info("\nResult: Total: {}, Passed: {}, Failed {}, Ignore {}\n", count, passed, failed, ignore);
    }
}
