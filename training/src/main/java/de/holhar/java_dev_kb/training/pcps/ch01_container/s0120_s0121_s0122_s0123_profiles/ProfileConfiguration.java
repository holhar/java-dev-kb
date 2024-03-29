package de.holhar.java_dev_kb.training.pcps.ch01_container.s0120_s0121_s0122_s0123_profiles;

import de.holhar.java_dev_kb.training.pcps.ch01_container.s0114_autowired_s0115_qualifier_s0117_s0118_beans.DependencyOne;
import de.holhar.java_dev_kb.training.pcps.ch01_container.s0114_autowired_s0115_qualifier_s0117_s0118_beans.DependencyTwo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Q1.20:
 * Beans defined in this configuration class will be only created for profiles "dev" and "qa".
 * Also, the DependencyTwo bean will NOT be created for "prod", specified by the explanation mark in front of the
 * profile name.
 */
@Profile({"dev", "qa"})
@Configuration
public class ProfileConfiguration {

    @Bean
    public DependencyOne dependencyOne() {
        return new DependencyOne("DevQaBean");
    }

    @Bean
    @Profile("!prod")
    public DependencyTwo dependencyTwo() {
        return new DependencyTwo("NotProdBean");
    }
}
