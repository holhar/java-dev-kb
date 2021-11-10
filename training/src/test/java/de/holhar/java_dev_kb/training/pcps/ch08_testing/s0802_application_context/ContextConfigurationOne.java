package de.holhar.java_dev_kb.training.pcps.ch08_testing.s0802_application_context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfigurationOne {

    @Bean
    public AwesomeBean firstAwesomeBean() {
        return new AwesomeBean("First awesome bean from ContextConfigurationOne");
    }
}
