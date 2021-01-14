package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec02_generics.subsec07_bounds;

import java.util.ArrayList;
import java.util.List;

public class ToughOnes {

    public static void main(String[] args) {
        List<?> list1 = new ArrayList<A>();
        List<? extends A> list2 = new ArrayList<A>();
        List<? super A> list3 = new ArrayList<A>();
        // List<? extends B> list4 = new ArrayList<A>(); // DOES NOT COMPILE
        List<? super B> list5 = new ArrayList<A>();
        // DOES NOT COMPILE - you need to know the exact type when instantiating an ArrayList
        // List<?> list6 = new ArrayList<? extends A>();
    }

    <B extends A> B method3(List<B> list) {
        // DOES NOT COMPILE
        //      B is a type parameter and the name of a class;
        //      inside the method it is just the type parameter and can not be instantiated directly
        // return new B();
        return null;
    }

    // DOES NOT COMPILE - tries to mix a method-specific type parameter with a wildcard
    // <X> void method5(List<X super B> list) { }
}

class A {
}

class B extends A {
}

class C extends B {
}
