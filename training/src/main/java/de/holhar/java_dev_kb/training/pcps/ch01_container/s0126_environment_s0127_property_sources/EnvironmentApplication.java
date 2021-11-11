package de.holhar.java_dev_kb.training.pcps.ch01_container.s0126_environment_s0127_property_sources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = "de.holhar.java_dev_kb.training.pcps.ch01_container.s0126_environment_s0127_property_sources")
public class EnvironmentApplication {

    private static final Logger logger = LoggerFactory.getLogger(EnvironmentApplication.class);

    /**
     * Q1.26:
     * The environment is part of the application container and contains profiles and properties.
     *
     * Q1.27:
     * Default properties of non-web applications:
     * - JVM system properties
     * - System environment variables
     *
     * Servlet-based web application contain three additional default properties:
     * - Servlet configuration properties
     * - Servlet context parameters
     * - JNDI properties
     *
     * There can be more property sources:
     * - Command line properties
     * - Application configuration (properties file)
     * - Server ports
     * - Management server
     *
     * The {@link org.springframework.context.ApplicationContext} interface extends
     * {@link org.springframework.core.env.EnvironmentCapable} - the latter is a SAM interface containing
     * "getEnvironment". This is how application context and environment are connected.
     */
    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(EnvironmentApplication.class, args);
    }

    @PostConstruct
    private void init() {
        logger.info("{}", environment.getDefaultProfiles());
    }
}
