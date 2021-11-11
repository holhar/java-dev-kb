package de.holhar.java_dev_kb.training.pcps.ch08_testing.s0805_context_configuration;

import de.holhar.java_dev_kb.training.pcps.ch01_container.s0104_application_context.config.SomeBeanOne;
import de.holhar.java_dev_kb.training.pcps.ch01_container.s0114_autowired_s0115_qualifier_s0117_s0118_beans.DependencyOne;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextLoader;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Q8.5:
 * @ContextConfiguration defines class-level metadata that is used to determine how to load and configure an
 * ApplicationContext for integration tests.
 */
@ContextConfiguration(
        // Configuration classes to create application context from. Default {}
        classes = {TestConfiguration.class},
        // Inherit initializers from test superclasses. Default 'true'
        inheritInitializers = true,
        // Inherit (@Configuration) classes or locations from test superclasses. Default 'true'
        inheritLocations = true,
        // Classes implementing the ApplicationContextInitializer interface. Default {}
        initializers = {TestContextInitializer.class},
        // Classes implementing the ContextLoader interface. Default {}
        //loader = {TestContextLoader.class},
        // Locations of XML configuration files to create application context from. Default {}
        locations = "src/webapp/WEB-INF/applicationContext.xml",
        // Alias for locations
        //value = "src/webapp/WEB-INF/applicationContext.xml"
        // Name of the context hierarchy level represented by this configuration.
        name = ""
)
class TestingSomething {

    // Bean configured via TestConfiguration.class
    @Autowired
    private DependencyOne dependencyOne;

    // Bean configured via XML configuration file
    @Autowired
    private SomeBeanOne someBeanOne;

    @Test
    void testSomething() {
        assertTrue(true);
    }
}
