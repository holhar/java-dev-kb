package de.holhar.java_dev_kb.training.pcps.ch02_aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * An Aspect brings together one or more pointcuts with one or more advices, typically grouping one cross-cutting
 * concern.
 */
@Aspect
@Component
public class Aspects {

    private static final Logger logger = LoggerFactory.getLogger(Aspects.class);

    /**
     * A Pointcut is a predicate that helps match an Advice to be applied by an Aspect at a particular JoinPoint.
     * We often associate the Advice with a Pointcut expression, and it runs at any Joinpoint matched by the Pointcut.
     *
     * An Advice is an action taken by an aspect at a particular Joinpoint. Different types of advice include “around,” “before,” and “after.”
     * In Spring, an Advice is modelled as an interceptor, maintaining a chain of interceptors around the Joinpoint.
     *
     * Waving is the process by which aspects and code is combined as to enable execution of cross cutting concerns
     * the joins points specified by the pointcuts in the aspects. Weaving can occur at compile time, load time or
     * runtime. For the latter Spring uses proxy objects in terms of JDK dynamic proxies and CGLIB proxies
     *
     * POI: https://www.baeldung.com/spring-aop-pointcut-tutorial
     */
    @Before(value = "execution(public void de.holhar.java_dev_kb.training.pcps.ch02_aop.ExampleService.doSomething())")
    public void addAdditionalBehaviour() {
        logger.info("Add behaviour before");
    }

    @Around(value = "execution(public void de.holhar.java_dev_kb.training.pcps.ch02_aop.ExampleService.doSomethingDifferent())")
    public Object addAdditionalBehaviourAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("add behaviour before {}", joinPoint.getSignature());
        final Object result = joinPoint.proceed();
        logger.info("add behaviour after {}", joinPoint.getSignature());
        return result;
    }
}
