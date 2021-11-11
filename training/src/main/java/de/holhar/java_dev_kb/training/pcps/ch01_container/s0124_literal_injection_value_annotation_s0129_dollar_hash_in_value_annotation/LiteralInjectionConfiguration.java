package de.holhar.java_dev_kb.training.pcps.ch01_container.s0124_literal_injection_value_annotation_s0129_dollar_hash_in_value_annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import javax.annotation.PostConstruct;

@Configuration
@PropertySource("classpath:propertysource.properties")
public class LiteralInjectionConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(LiteralInjectionConfiguration.class);

    /**
     * Q1.24:
     * Literals (which can have their origin in env variables, property files, Spring beans etc.) can be injected with
     * the @Value annotation.
     *
     * @Value can be applied at the field or method/constructor parameter level that indicates a default value
     * expression for the affected argument.
     * Typically used for expression-driven dependency injection (that is, injecting default values, property values,
     * evaluated SpEL expressions or values from other beans). Also supported for dynamic resolution of handler
     * method parameters, e.g. in Spring MVC.
     *
     * A common use case is to assign default field values using "#{systemProperties.myProp}" style expressions.
     * Note that actual processing of the @Value annotation is performed by a BeanPostProcessor which in turn means
     * that you cannot use @Value within BeanPostProcessor or BeanFactoryPostProcessor types.
     *
     * Q1.29: Expression starting with $ - references a property name in application environment (evaluated by
     * {@link org.springframework.context.support.PropertySourcesPlaceholderConfigurer})
     */
    @Value("${timeout.initial:${timeout.fallback}}")
    private int timeoutMillis;

    /**
     * Injecting a value that results from a SpEl expression
     *
     * Q1.29: Expression starting with # - a dedicated SpEL expression parsed by {@link SpelExpressionParser} and
     * evaluated by SpEL expression instance
     */
    @Value("#{ T(java.lang.Math).random() * 50.0 }")
    private double randomDouble;

    private String someValue;
    private String anotherValue;
    private String yetAnotherValue;

    @Value("injectThis!")
    public void setSomeValue(String someValue) {
        this.someValue = someValue;
    }

    @Autowired
    public void setAnotherValue(@Value("onlyInjectedInConjuctionWithAutowired") String anotherValue) {
        this.anotherValue = anotherValue;
    }

    public LiteralInjectionConfiguration(@Value("alsoInjectedWithoutAutowired") String yetAnotherValue) {
        this.yetAnotherValue = yetAnotherValue;
    }

    @PostConstruct
    private void logInjectedLiterals() {
        logger.info("timeoutMillis: {}", timeoutMillis);
        logger.info("randomDouble: {}", randomDouble);
        logger.info("someValue: {}", someValue);
        logger.info("anotherValue: {}", anotherValue);
        logger.info("yetAnotherValue: {}", yetAnotherValue);
    }
}
