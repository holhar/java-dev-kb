package de.holhar.java_dev_kb.training.pcps.ch08_testing.s0802_application_context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfigurationTwo {

    @Bean
    public AwesomeBean secondAwesomeBean() {
        return new AwesomeBean("Second awesome bean from ContextConfigurationOne");
    }
}
