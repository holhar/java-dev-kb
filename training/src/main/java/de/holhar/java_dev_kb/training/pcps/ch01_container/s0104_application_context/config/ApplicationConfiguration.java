package de.holhar.java_dev_kb.training.pcps.ch01_container.s0104_application_context.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public SomeBeanOne someBeanOne() {
        return new SomeBeanOne();
    }

    @Bean
    public SomeBeanTwo someBeanTwo() {
        return new SomeBeanTwo();
    }
}
