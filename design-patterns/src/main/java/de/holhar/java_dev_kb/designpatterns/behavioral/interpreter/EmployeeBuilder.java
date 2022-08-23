package de.holhar.java_dev_kb.designpatterns.behavioral.interpreter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeBuilder {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeBuilder.class);

    public enum Operator {
        AND, OR, NOT
    }

    public Employee buildExpression(Employee emp1, Employee emp2, Operator operator) {
        switch (operator) {
            case OR:
                return new OrExpression(emp1, emp2);
            case AND:
                return new AndExpression(emp1, emp2);
            case NOT:
                return new NotExpression(emp1);
            default:
                logger.warn("Only AND, OR, and NOT operators are allowed at present");
                return null;
        }
    }
}
