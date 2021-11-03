package de.holhar.java_dev_kb.training.pcps.ch08_testing.s0802_application_context;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SpringRunner to apply test with Spring TestContext Framework, that is, with an application context.
 *
 * Typically, used for integration tests to validate interaction between components. In general, avoided for unit
 * tests, which would mock or stub dependencies of the class under test.
 */
@RunWith(SpringRunner.class)
/**
 * @ContextConfiguration defines class-level metadata that is used to determine how to load and configure an
 * ApplicationContext for integration tests.
 *
 * In this example: load bean configuration from {@link ContextConfigurationOne} and making it available for
 * inheriting test classes, thus creating a shared application context for JUnit integration tests.
 */
@ContextConfiguration(classes = ContextConfigurationOne.class)
public class TestBaseClass {
}
