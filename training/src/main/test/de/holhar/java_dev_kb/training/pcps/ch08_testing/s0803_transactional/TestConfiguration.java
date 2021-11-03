package de.holhar.java_dev_kb.training.pcps.ch08_testing.s0803_transactional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@ComponentScan(value = "de.holhar.java_dev_kb.training.pcps.ch08_testing.s0803_transactional")
public class TestConfiguration {
}
