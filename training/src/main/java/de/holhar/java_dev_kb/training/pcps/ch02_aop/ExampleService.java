package de.holhar.java_dev_kb.training.pcps.ch02_aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    public final String name;

    public ExampleService() {
        this.name = "nameSomething";
    }

    private static final Logger logger = LoggerFactory.getLogger(ExampleService.class);

    /**
     * A Joinpoint is a point during the execution of a program, such as the execution of a method or the handling of an exception.
     * In Spring AOP, a JoinPoint always represents a method execution.
     */
    public String doSomething(String value, int number) {
        logger.info("Doing something");
        if (number % 2 == 0) {
            throw new IllegalArgumentException("Something terrible happened");
        }
        return "doSomethingReturnValue";
    }

    public void doSomethingDifferent() {
        logger.info("Dong something different");
    }
}
