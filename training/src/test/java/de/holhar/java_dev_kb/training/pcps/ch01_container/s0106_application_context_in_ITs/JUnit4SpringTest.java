package de.holhar.java_dev_kb.training.pcps.ch01_container.s0106_application_context_in_ITs;

import de.holhar.java_dev_kb.training.pcps.ch01_container.TestConfiguration;
import de.holhar.java_dev_kb.training.pcps.ch01_container.s0104_application_context.config.SomeBeanOne;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

// Ensure context is loaded by using @RunWith in conjunction with SpringRunner
//@RunWith(SpringRunner.class)
// Optionally specify XML configuration file or Java configuration class to be loaded into application context
// (otherwise the configuration of the active profile will be loaded)
@ContextConfiguration(classes = TestConfiguration.class)
public class JUnit4SpringTest {

    private static final Logger logger = LoggerFactory.getLogger(JUnit4SpringTest.class);

    @Autowired
    private SomeBeanOne someBeanOne;

    @Autowired
    private ApplicationContext applicationContext;

    //@Test
    public void contextLoads() {
        logger.info("SomeBeanOne name: '{}'", someBeanOne.getName());
        logger.info("Application context: '{}'", applicationContext);
    }

}
