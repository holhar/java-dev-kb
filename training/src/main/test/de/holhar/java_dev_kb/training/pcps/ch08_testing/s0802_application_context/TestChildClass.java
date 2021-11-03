package de.holhar.java_dev_kb.training.pcps.ch08_testing.s0802_application_context;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Inherits test application context defined in {@link TestBaseClass} and adds additional configuration by specifying
 * @ContextConfiguration with config {@link ContextConfigurationTwo}.
 *
 * NOTE: Bean with the same id in {@link ContextConfigurationTwo} as in {@link ContextConfigurationOne} would replace
 * the ones in {@link ContextConfigurationOne}
 *
 * NOTE TOO: The established test ApplicationContext (or WebApplicationContext) is cached and reused between tests as
 * long as the source for the test configuration is the same (e.g. defined by @ContextConfiguration, @ActiveProfiles,
 * @TestPropertySource, etc.). As a consequence, there may be side effects between tests - one test may change a bean
 * in the application context in such a way, that it breaks another test. In these circumstances the annotation
 * {@link org.springframework.test.annotation.DirtiesContext} can help.
 */
@ContextConfiguration(classes = ContextConfigurationTwo.class)
public class TestChildClass extends TestBaseClass {

    private static final Logger logger = LoggerFactory.getLogger(TestChildClass.class);

    @Autowired
    @Qualifier("firstAwesomeBean")
    private AwesomeBean awesomeBeanOne;

    @Autowired
    @Qualifier("secondAwesomeBean")
    private AwesomeBean awesomeBeanTwo;

    @Test
    public void awesomeBeanOne_fromContextConfigurationOne_isPresent() {
        assertNotNull(awesomeBeanOne);
        assertTrue(awesomeBeanOne.getName().contains("ContextConfigurationOne"));
        logger.info(awesomeBeanOne.getName());
    }

    @Test
    public void awesomeBeanTwo_fromContextConfigurationTwo_isPresent() {
        assertNotNull(awesomeBeanTwo);
        assertTrue(awesomeBeanTwo.getName().contains("ContextConfigurationOne"));
        logger.info(awesomeBeanTwo.getName());
    }
}
