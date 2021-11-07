package de.holhar.java_dev_kb.training.pcps.ch08_testing.s0805_context_configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextLoader;

public class TestContextLoader implements ContextLoader {

    @Override
    public String[] processLocations(Class<?> clazz, String... locations) {
        return locations;
    }

    @Override
    public ApplicationContext loadContext(String... locations) throws Exception {
        return null;
    }
}
