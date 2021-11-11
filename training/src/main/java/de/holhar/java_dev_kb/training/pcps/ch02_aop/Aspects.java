package de.holhar.java_dev_kb.training.pcps.ch02_aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * An Aspect brings together one or more pointcuts with one or more advices, typically grouping one cross-cutting
 * concern.
 *
 * @Aspect annotation is necessary to mark the class, in order for AspectJ to recognize as such. Additionally it's
 * important to note, that it must be ensured, that the class is available as Spring bean (e.g. by annotating it with
 * @Component
 */
@Aspect
@Component
public class Aspects {

    private static final Logger logger = LoggerFactory.getLogger(Aspects.class);

    /**
     * Q2.1/Q2.2/Q2.3: CONCEPTS
     *
     * A Pointcut is a predicate that helps match an Advice to be applied by an Aspect at a particular joinPoint.
     * We often associate the Advice with a Pointcut expression, and it runs at any Joinpoint matched by the Pointcut.
     *
     * An Advice is an action taken by an aspect at a particular Joinpoint. Different types of advice include “around,” “before,” and “after.”
     * In Spring, an Advice is modelled as an interceptor, maintaining a chain of interceptors around the Joinpoint.
     *
     * Waving is the process by which aspects and code is combined as to enable execution of cross-cutting concerns
     * the joins points specified by the pointcuts in the aspects. Weaving can occur at compile time, load time or
     * runtime. For the latter Spring uses proxy objects in terms of JDK dynamic proxies and CGLIB proxies
     *
     * POI: https://www.baeldung.com/spring-aop-pointcut-tutorial
     */

    /**
     * Q2.6: POINTCUTS - START
     */

    /**
     * Basic structure of pointcut expression:
     * - [designator] [pattern]
     * - e.g. "execution(* de..*Service.*(..)"
     *
     * Pointcuts are combinable by logical operators:
     * - '!' - Not; negates the pointcut expression
     * - '&&' - And; logical and of pointcut expressions
     * - '||' - Or; logical or of pointcut expressions
     *
     * Designators:
     * - execution (matches method execution joinPoints); pattern (some pattern parts are negatable):
     * -- [(!)method visibility] [(!)return type] [package].[class].[method]([parameters] [(!)throws exceptions])
     *
     * - within (matches joinPoints located in one or more classes - packages are optional); pattern:
     * -- [package].[class]
     *
     * - this (matches all joinPoints where the currently executing object is of specified type - PROXY OBJECT); example:
     * -- this(MyServiceType)
     *
     * - target (matches all joinPoints where the currently executing object is of specified type - TARGET OBJECT); example:
     * -- target(MyServiceType)
     *
     * - args (matches joinPoints by arguments of specified types); example:
     * -- args(long, long)
     * -- args(java.util.*) <= matches joinPoints where all arguments are of any type from the java.util package
     *
     * - @target (matches joinPoints in classes annotated with the specified annotation); example:
     * -- @target(org.springframework.stereotype.Service)
     *
     * - @args (matches joinPoints where an arguments type is annotated with the specified annotation); example:
     * -- @args(org.springframework.stereotype.Service)
     *
     * - @within (matches joinPoints in classes annotated with specified annotation); example:
     * -- @within(org.springframework.stereotype.Service)
     *
     * - @annotation (matches joinPoints in methods annotated with specified annotation); example:
     * -- @annotation(org.springframework.stereotype.Service)
     *
     * - bean (matches joinPoints in specified bean); example:
     * -- bean(myServiceClass)
     *
     */

    /**
     * Pointcut that selects joinPoints in packages below "de" for classes containing "Service" in name for arbitrary
     * return types and parameter numbers.
     *
     * NOTE: Wildcards can be applied to return types, packages / package paths, class names and methods, as well as
     * method parameters.
     */
    @Pointcut("execution(* de..*.Service.*(..))")
    public void applicationServiceMethodPointcut() {}

    /**
     * Pointcut that selects joinPoints being public method executions.
     */
    @Pointcut("execution(public * *(..))")
    public void publicMethodPointcut() {}

    /**
     * Pointcut selecting all joinPoints below the package "pcps".
     */
    @Pointcut("within(de.holhar.java_dev_kb.training.pcps..*)")
    public void inPcpsPackagePointcut() {}

    /**
     * Pointcut that selects joinPoints on Spring bean with the name "exampleService".
     */
    @Pointcut("bean(exampleService)")
    public void exampleServiceBeanPointcut() {}

    /**
     * Pointcut that combines all the above pointcuts to select joinPoints that match all the pointcuts in conjunction.
     *
     * NOTE: Pointcuts of all types can be combined using the logical operators '&&', '||' and '!'.
     */
    @Pointcut("publicMethodPointcut() && inPcpsPackagePointcut() && applicationServiceMethodPointcut() && inPcpsPackagePointcut()")
    public void publicServiceMethodInPcpsPackagePointcut() {}

    /**
     * POINTCUTS - END
     */

    /**
     * Q2.5: ADVICES - START
     */

    /**
     * Before advice - executed before a joinPoint; can not prevent proceeding to the joinPoint unless the advice
     * throws an exception
     *
     * Use cases: access control (security - throw exception for unauthorized users), statistics (counting method
     * invocations)
     *
     * NOTE: JoinPoint param is optional, but must be the first if present. JoinPoint holds information about type of
     * joinPoint, signature of executed method, target object (bean), currently executing object (proxy object)
     */
    @Before("execution(public String de..*ExampleService.doSomething(..))")
    public void beforeAdviceExample(JoinPoint inJoinPoint) {
        final Object[] args = inJoinPoint.getArgs();
        logger.info("Before advice example for '{}'", inJoinPoint.getSignature().toShortString());
        Arrays.stream(args).forEach(arg -> logger.info(" - '{}'", arg));
    }

    /**
     * After returning advice - executed after execution of a joinPoint; completed without throwing an exception.
     *
     * Use cases: statistics (counting successful executions), data validation (validating return value)
     *
     * NOTE: JoinPoint param is optional, but must be the first if present. JoinPoint holds information about type of
     * joinPoint, signature of executed method, target object (bean), currently executing object (proxy object)
     */
    @AfterReturning(pointcut = "execution(public String de..*ExampleService.doSomething(..))",
                    returning = "inReturnValue")
    public void afterReturningAdviceExample(JoinPoint inJoinPoint, Object inReturnValue) {
        logger.info("After returning example for '{}'", inJoinPoint.getSignature().toShortString());
        logger.info(" - return value: '{}'", inReturnValue);
    }

    /**
     * After throwing advice - executed after execution of a joinPoint that resulted in an exception being thrown.
     *
     * Use cases: error handling (save error infos, send alerts, attempt recovery), statistics (count occurred errors)
     *
     * NOTE: JoinPoint param is optional, but must be the first if present. JoinPoint holds information about type of
     * joinPoint, signature of executed method, target object (bean), currently executing object (proxy object)
     */
    @AfterThrowing(pointcut = "execution(public String de..*ExampleService.doSomething(..))", throwing = "inException")
    public void afterThrowingAdviceExample(JoinPoint inJoinPoint, Throwable inException) {
        logger.info("After throwing example for '{}'", inJoinPoint.getSignature().toShortString());
        logger.info(" - exception thrown '{}', with message: '{}'", inException.getClass().getSimpleName(),
                inException.getMessage());
    }

    /**
     * After (finally) advice - executed after execution of a joinPoint, regardless of whether an exception was
     * thrown or not
     *
     * Use-cases: releasing resources
     *
     * NOTE: JoinPoint param is optional, but must be the first if present. JoinPoint holds information about type of
     * joinPoint, signature of executed method, target object (bean), currently executing object (proxy object)
     */
    @After("execution(public String de..*ExampleService.doSomething(..))")
    public void afterFinallyAdviceExample(JoinPoint inJoinPoint) {
        logger.info("After finally example for '{}'", inJoinPoint.getSignature().toShortString());
    }

    /**
     * Around advice - executed before and after a joinPoint; can choose to execute the joinPoint or not. Can choose 
     * to return any return value from the execution of the joinPoint or return some other return value.
     *
     * NOTE: Is the only advice capable of catching and handling exceptions that can occur during processing of the
     * joinPoint.
     *
     * Q2.8: ProceedingJoinPoint is optional, but must be the first param, if present. It holds information
     * about type of joinPoint, signature of executed method, target object (bean), currently executing object (proxy
     * object). Also, ProceedingJoinPoint interface adds to proceed methods to control the flow of the joinPoint
     * method: proceed() and process(Object[] args)
     */
    @Around("execution(public void de.holhar.java_dev_kb.training.pcps.ch02_aop.ExampleService.doSomethingDifferent())")
    public Object addAdditionalBehaviourAround(ProceedingJoinPoint inProceedingJoinPoint) throws Throwable {
        logger.info("Around advice before example for '{}'", inProceedingJoinPoint.getSignature().toShortString());
        Object result = null;
        try {
            result = inProceedingJoinPoint.proceed();
        } catch (IllegalArgumentException e) {
            logger.warn("IllegalArgumentException occurred during joinPoint execution, message: '{}'", e.getMessage());
            return result;
        }
        logger.info("Around advice after example for '{}'", inProceedingJoinPoint.getSignature().toShortString());
        return result;
    }

    /**
     * ADVICES - END
     */
}
