package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec06_enums;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class SeasonHandler {

    public static void main(String[] args) {
        Season summer = Season.SUMMER;

        switch (summer) {
            // Only provide the value of the enum - the compiler already knows about the type
            case SPRING:
                println("Yay, it's spring");
                break;
            case SUMMER:
                println("Yay, it's summer");
                break;
            case FALL:
                println("Hm, it's fall...");
                break;
            case WINTER:
                println("Oh no, it's winter :-(");
                break;
            default:
                println("What the hell is going on?");
                // Also, int values for comparing via the ordinal (Season.SUMMER.ordinal()) is not valid
                // case 0:
                //  doSomething();
                //  beak;
        }
    }
}
