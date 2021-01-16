package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec06_enums;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public enum Season2 {

    WINTER {
        public void printHours() {
            println("9am - 3pm");
        }

        public void printSomething() {
            println("Print something different");
        }
    }, SPRING {
        public void printHours() {
            println("9am - 5pm");
        }
    }, SUMMER {
        public void printHours() {
            println("9am - 7pm");
        }

        public void printSomething() {
            println("Print bla bla");
        }
    }, FALL {
        public void printHours() {
            println("9am - 5pm");
        }
    };

    // Provide abstract method, that NEEDS TO be implemented by every enum value
    public abstract void printHours();

    // Provide concrete method, that CAN be overridden by the enum value
    public void printSomething() {
        println("Print something");
    }
}
