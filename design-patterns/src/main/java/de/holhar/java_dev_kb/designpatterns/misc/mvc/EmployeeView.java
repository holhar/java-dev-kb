package de.holhar.java_dev_kb.designpatterns.misc.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeView {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeView.class);

    public void printEmployeeInformation(Employee emp) {
        logger.info("Employee view:\n'{}'", emp);
    }
}
