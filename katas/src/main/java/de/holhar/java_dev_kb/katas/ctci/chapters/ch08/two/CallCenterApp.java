package de.holhar.java_dev_kb.katas.ctci.chapters.ch08.two;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;

public class CallCenterApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(CallCenterApp.class);

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void main(String[] args) {

        final Predicate<Employee> isResponder = employee -> employee.getClass().toString().contains("Responder");
        final Predicate<Employee> isManager = employee -> employee.getClass().toString().contains("Manager");
        final Predicate<Employee> isDirector = employee -> employee.getClass().toString().contains("Director");

        CallCenter callCenter = new CallCenter();
        setupEmployees(callCenter);

        int responderNum = (int) callCenter.getEmployees().stream().filter(isResponder).count();
        int managerNum = (int) callCenter.getEmployees().stream().filter(isManager).count();
        int directorNum = (int) callCenter.getEmployees().stream().filter(isDirector).count();

        callCenter.getScheduler().initScheduler(responderNum, managerNum, directorNum);

        callCenter.addCall(new Call());
        callCenter.addCall(new Call());
        callCenter.addCall(new Call());
        callCenter.addCall(new Call());
        callCenter.addCall(new Call());
        callCenter.addCall(new Call());
        callCenter.addCall(new Call());
        callCenter.addCall(new Call());
        callCenter.addCall(new Call());
        callCenter.addCall(new Call());

        LOGGER.debug("{}", callCenter.getScheduler().getFreeResponderNum());
        LOGGER.debug("{}", callCenter.getScheduler().getFreeManagerNum());
        LOGGER.debug("{}", callCenter.getScheduler().getFreeDirectorNum());
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void setupEmployees(CallCenter callCenter) {
        callCenter.addEmployee(new Responder("Peter"));
        callCenter.addEmployee(new Responder("Max"));
        callCenter.addEmployee(new Responder("Tim"));
        callCenter.addEmployee(new Responder("Tom"));
        callCenter.addEmployee(new Responder("Moritz"));
        callCenter.addEmployee(new Responder("Ludwig"));
        callCenter.addEmployee(new Responder("Robert"));
        callCenter.addEmployee(new Responder("Larry"));
        callCenter.addEmployee(new Manager("Petra"));
        callCenter.addEmployee(new Manager("Jenny"));
        callCenter.addEmployee(new Manager("Cherry"));
        callCenter.addEmployee(new Director("Laura"));
    }

}
