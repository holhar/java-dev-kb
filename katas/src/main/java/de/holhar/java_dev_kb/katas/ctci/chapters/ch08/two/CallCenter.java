package de.holhar.java_dev_kb.katas.ctci.chapters.ch08.two;

import java.util.ArrayList;
import java.util.List;

public class CallCenter<T extends Employee> {

    private final List<T> employees;
    private final List<Call> calls;
    private final Scheduler scheduler;

    public CallCenter() {
        employees = new ArrayList<>();
        calls = new ArrayList<>();
        scheduler = Scheduler.getInstance();
    }

    public void addEmployee(T employee) {
        employees.add(employee);
    }

    public void addCall(Call call) {
        calls.add(call);
        scheduler.dispatchCall(call);
    }

    public List<T> getEmployees() {
        return new ArrayList<>(employees);
    }

    public List<Call> getCalls() {
        return new ArrayList<>(calls);
    }

    public Scheduler getScheduler() {
        return scheduler;
    }
}
