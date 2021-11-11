package de.holhar.java_dev_kb.training.pcps.ch01_container.s0119_nonfinal_configuration_classes;

import org.springframework.context.annotation.Configuration;

/**
 * Q1.19:
 * My not be final. The configuration class will be proxied by Spring container, in order to be able to create an
 * manage singleton beans. That is, the class must be subclassed and corresponding Bean methods must be overridden.
 * This is why neither the class, nor the methods must be final.
 *
 * The configuration proxy will forward calls to create a bean only when the corresponding methods is called for the
 * first time. It will keep a reference for the created bean instance. For subsequent calls, the created bean
 * instance will be returned directly, without forwarding to bean creation methods.
 */
@Configuration
public class NonFinalConfiguration {
}
