package de.holhar.java_dev_kb.training.pcps.ch01_container.s0106_application_context_in_ITs;

import de.holhar.java_dev_kb.training.pcps.ch01_container.TestConfiguration;
import de.holhar.java_dev_kb.training.pcps.ch01_container.s0104_application_context.config.SomeBeanOne;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/*
 * @SpringJunitConfig combines @ExtendWith(SpringExtension.class) with @ContextConfiguration
 */
@SpringJUnitConfig(classes = TestConfiguration.class)
class JUnit5SpringTest {

    private static final Logger logger = LoggerFactory.getLogger(JUnit4SpringTest.class);

    @Autowired
    private SomeBeanOne someBeanOne;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        logger.info("SomeBeanOne name: '{}'", someBeanOne.getName());
        logger.info("Application context: '{}'", applicationContext);
    }
}
