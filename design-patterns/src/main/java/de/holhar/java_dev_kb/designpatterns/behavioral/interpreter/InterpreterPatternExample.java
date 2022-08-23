package de.holhar.java_dev_kb.designpatterns.behavioral.interpreter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class InterpreterPatternExample {

    private static final Logger logger = LoggerFactory.getLogger(InterpreterPatternExample.class);

    public static void main(String[] args) {
        Context context = new Context(10, Arrays.asList("G2", "G3"));

        IndividualEmployee emp1 = new IndividualEmployee(5, "G1");
        IndividualEmployee emp2 = new IndividualEmployee(10, "G2");
        IndividualEmployee emp3 = new IndividualEmployee(15, "G3");
        IndividualEmployee emp4 = new IndividualEmployee(20, "G4");

        EmployeeBuilder builder = new EmployeeBuilder();

        logger.info("emp1 is eligible for promotion: '{}'", emp1.interpret(context));
        logger.info("emp2 is eligible for promotion: '{}'", emp2.interpret(context));
        logger.info("emp3 is eligible for promotion: '{}'", emp3.interpret(context));
        logger.info("emp4 is eligible for promotion: '{}'", emp4.interpret(context));

        logger.info("Is either emp1 or emp3 eligible for promotion? '{}'", builder.buildExpression(emp1, emp3,
                EmployeeBuilder.Operator.OR).interpret(context));
        logger.info("Are both emp2 and emp4 eligible for promotion? '{}'", builder.buildExpression(emp2, emp4,
                EmployeeBuilder.Operator.AND).interpret(context));
        logger.info("The statement 'emp3 is NOT eligible for promotion' true? '{}'", builder.buildExpression(emp3, null,
                EmployeeBuilder.Operator.NOT).interpret(context));
    }
}
