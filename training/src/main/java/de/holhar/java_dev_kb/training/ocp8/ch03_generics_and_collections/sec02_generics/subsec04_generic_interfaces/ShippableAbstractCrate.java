package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec02_generics.subsec04_generic_interfaces;

/**
 * @author hhs@dasburo.com
 *
 * Second approach: create a generic class
 */
public class ShippableAbstractCrate<U> implements Shippable<U> {
    public void ship(U u) {
    }
}
