package de.holhar.java_dev_kb.designpatterns.misc.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MvcPatternExample {

    private static final Logger logger = LoggerFactory.getLogger(MvcPatternExample.class);

    public static void main(String[] args) {
        Employee employee = new Employee(10, "Max Power", "Superstar");
        EmployeeView view = new EmployeeView();

        EmployeeController controller = new EmployeeController(employee, view);
        controller.updateView();

        logger.info("\nVIEW UPDATES generated automatically by the controller: ");
        logger.info("------------------------------------------------------- ");

        controller.setEmployeeJob("Programmer");
        controller.setEmployeeJob("Manager");
        controller.setEmployeeName("Vic Vega");
        controller.setEmployeeId(200);
    }
}
