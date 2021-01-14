package de.holhar.java_dev_kb.training.oca8.ch04_methods_and_encapsulation;

public class VarArgUsage {

    // There is only one vararg parameter allowed per method,
    // and it has to be the last parameter in the signature
    public void someMethod(String values, int... nums) {
    }

    // The three dots '...' must be in front of the type:
    //public void someMethod2(String values, ...int nums) {}
}
