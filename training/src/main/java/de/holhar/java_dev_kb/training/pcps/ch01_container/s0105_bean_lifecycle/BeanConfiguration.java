package de.holhar.java_dev_kb.training.pcps.ch01_container.s0105_bean_lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean(initMethod = "initMethod", destroyMethod = "tearDown")
    public YetAnotherBean yetAnotherBean() {
        return new YetAnotherBean();
    }
}
