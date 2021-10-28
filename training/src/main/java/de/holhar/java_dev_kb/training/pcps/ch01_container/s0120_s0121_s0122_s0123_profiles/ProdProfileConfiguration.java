package de.holhar.java_dev_kb.training.pcps.ch01_container.s0120_s0121_s0122_s0123_profiles;

import de.holhar.java_dev_kb.training.pcps.ch01_container.s0114_autowired_s0115_qualifier_s0117_s0118_beans.DependencyThree;
import org.springframework.context.annotation.Bean;

@ProdConfiguration
public class ProdProfileConfiguration {

    @Bean
    public DependencyThree dependencyThree() {
        return new DependencyThree();
    }
}
