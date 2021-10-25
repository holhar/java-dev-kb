package de.holhar.java_dev_kb.training.pcps.ch01_container;

import de.holhar.java_dev_kb.training.pcps.ch01_container.s0104_application_context.config.SomeBeanOne;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    @Bean
    public SomeBeanOne someBeanOne() {
        final SomeBeanOne someBeanOne = new SomeBeanOne();
        someBeanOne.setName("foobar");
        return someBeanOne;
    }
}
