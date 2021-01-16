package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec02_generics.subsec05_generic_methods;

import de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec02_generics.subsec03_generic_classes.Crate;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class GenericMethods {

    /*
     * Method declaration parts:
     * - formal type parameter: <T>
     *     |-> Unless a method is obtaining the generic formal type parameter from the class/interface, it is specified
     *         immediately before the return type of the method - this can lead to interesting looking code (see below)
     * - return type:           Crate<T>
     * - method paramter:       T
     */
    public static <T> Crate<T> ship(T t) {
        println("Preparing " + t);
        return new Crate<>();
    }

    public static <T> void sink(T t) {
    }

    public static <T> T identify(T t) {
        return t;
    }

    /*
     * DOES NOT COMPILE - no formal type parameter given
     */
    //public static T noGood(T t) {
    //    return t;
    //}
}
