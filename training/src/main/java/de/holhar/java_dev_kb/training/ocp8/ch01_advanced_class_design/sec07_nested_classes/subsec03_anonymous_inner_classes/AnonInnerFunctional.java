package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec07_nested_classes.subsec03_anonymous_inner_classes;

/**
 * @author hhs@dasburo.com
 */
public class AnonInnerFunctional {

    public int pay() {
        // Providing the anonymous inner class directly as function parameter:
        return admission(3, new SaleTodayOnly() {
            @Override
            public int dollarsOff() {
                return 3;
            }
        });
    }

    public int admission(int basePrice, SaleTodayOnly sale) {
        return basePrice - sale.dollarsOff();
    }

    interface SaleTodayOnly {
        int dollarsOff();
    }
}
