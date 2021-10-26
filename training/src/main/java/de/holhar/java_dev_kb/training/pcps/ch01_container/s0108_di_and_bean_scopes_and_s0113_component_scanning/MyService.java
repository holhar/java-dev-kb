package de.holhar.java_dev_kb.training.pcps.ch01_container.s0108_di_and_bean_scopes_and_s0113_component_scanning;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyService {
}
