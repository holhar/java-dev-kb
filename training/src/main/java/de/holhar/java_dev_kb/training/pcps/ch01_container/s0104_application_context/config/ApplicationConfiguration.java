package de.holhar.java_dev_kb.training.pcps.ch01_container.s0104_application_context.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public SomeBeanOne someBeanOne() {
        final SomeBeanOne someBeanOne = new SomeBeanOne();
        someBeanOne.setName("someBeanOne");
        return someBeanOne;
    }

    @Bean
    public SomeBeanTwo someBeanTwo() {
        final SomeBeanTwo someBeanTwo = new SomeBeanTwo();
        someBeanTwo.setName("someBeanTwo");
        return someBeanTwo;
    }
}
