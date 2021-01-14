package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec02_generics.subsec04_generic_interfaces;

/**
 * @author hhs@dasburo.com
 * <p>
 * There are three ways to implement this interface
 */
public interface Shippable<T> {
    void ship(T t);
}
