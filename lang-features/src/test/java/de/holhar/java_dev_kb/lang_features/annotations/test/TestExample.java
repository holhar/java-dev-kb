package de.holhar.java_dev_kb.lang_features.annotations.test;

import de.holhar.java_dev_kb.lang_features.annotations.test.core.Test;
import de.holhar.java_dev_kb.lang_features.annotations.test.core.TesterInfo;

@TesterInfo(
        priority = TesterInfo.Priority.HIGH,
        createdBy = "Holger Harms",
        tags = {"annotations","test"}
)
public class TestExample {

    @Test
    void testA() {
        if (true) {
            throw new RuntimeException("This will always fail");
        }
    }

    @Test(enabled = false)
    void testB() {
        if (false) {
            throw new RuntimeException("This will always pass");
        }
    }

    @Test(enabled = true)
    void testC() {
        if (10 > 1) {
            // nothing happens, it will pass
        }
    }
}
