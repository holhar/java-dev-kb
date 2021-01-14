package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec02_using_executor_service;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class AmbiguousLambdaSample {

    public static void useCallable(Callable<Integer> expression) {
    }

    public static void useSupplier(Supplier<Integer> expression) {
    }

    public static void use(Supplier<Integer> expression) {
    }

    public static void use(Callable<Integer> expression) {
    }

    public static void main(String[] args) {
        useCallable(() -> {
            throw new IOException();
        });      // COMPILES
        // useSupplier(() -> {throw new IOException();});   // DOES NOT COMPILE - Supplier does not support checked Exceptions
        // use(() -> {throw new IOException();});           // DOES NOT COMPILE - ambiguous lambda, the compiler does
        //                  not know which method is the target -> can be solved with explicit cast:
        use((Callable<Integer>) () -> {
            throw new IOException();
        });
    }
}
