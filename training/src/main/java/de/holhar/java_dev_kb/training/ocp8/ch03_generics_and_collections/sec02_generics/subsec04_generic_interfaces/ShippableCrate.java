package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec02_generics.subsec04_generic_interfaces;

/**
 * @author hhs@dasburo.com
 *
 * Third approach: Don't use generics at all (the old type unsafe way of writing code with 'raw types').
 */
public class ShippableCrate implements Shippable {
    public void ship(Object t) {
    }
}
