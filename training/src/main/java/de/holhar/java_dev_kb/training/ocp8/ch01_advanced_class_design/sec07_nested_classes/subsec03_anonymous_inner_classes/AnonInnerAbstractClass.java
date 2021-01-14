package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec07_nested_classes.subsec03_anonymous_inner_classes;

/**
 * @author hhs@dasburo.com
 * <p>
 * An anonymous inner class is a special case of a local inner class that does not have a name. It is declared and
 * instantiated all in one statement using the new keyword. Anonymous inner classes are required to extend an
 * existing class or implement an existing interface. They are useful when you have a short implementation that will
 * not be used anywhere else.
 */
public class AnonInnerAbstractClass {

    public int admission(int basePrice) {

        // The sale variable gets assigned an anonymous inner class
        SaleTodayOnly sale = new SaleTodayOnly() {
            @Override
            int dollarsOff() {
                return 3;
            }
        };

        return basePrice - sale.dollarsOff();
    }

    // Abstract class
    abstract class SaleTodayOnly {
        abstract int dollarsOff();
    }
}
