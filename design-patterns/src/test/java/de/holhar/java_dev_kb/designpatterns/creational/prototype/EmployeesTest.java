package de.holhar.java_dev_kb.designpatterns.creational.prototype;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EmployeesTest {

    @Test
    void test() throws CloneNotSupportedException {
        Employees employees = new Employees();
        employees.loadData();

        Employees employeesClone = (Employees) employees.clone();

        assertEquals(employees.getEmployeeList(), employeesClone.getEmployeeList());
    }
}
