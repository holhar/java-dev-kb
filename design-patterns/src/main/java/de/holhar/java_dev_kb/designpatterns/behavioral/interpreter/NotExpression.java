package de.holhar.java_dev_kb.designpatterns.behavioral.interpreter;

public class NotExpression implements Employee {

    private final Employee emp;

    public NotExpression(Employee emp) {
        this.emp = emp;
    }

    @Override
    public boolean interpret(Context context) {
        return !emp.interpret(context);
    }
}
