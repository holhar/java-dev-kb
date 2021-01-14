package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec07_nested_classes.subsec03_anonymous_inner_classes;

/**
 * @author hhs@dasburo.com
 */
public class AnonInnerInterface {

    public int admission(int basePrice) {

        SaleTodayOnly sale = new SaleTodayOnly() {

            @Override
            public int dollarsOff() {
                return 3;
            }
        };

        return basePrice - sale.dollarsOff();
    }

    interface SaleTodayOnly {
        int dollarsOff();
    }
}
