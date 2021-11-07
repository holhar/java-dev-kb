package de.holhar.java_dev_kb.training.pcps.ch08_testing.s0805_context_configuration;

import de.holhar.java_dev_kb.training.pcps.ch01_container.s0114_autowired_s0115_qualifier_s0117_s0118_beans.DependencyOne;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    @Bean
    public DependencyOne dependencyOne() {
        return new DependencyOne("ContextConfiguration");
    }
}
