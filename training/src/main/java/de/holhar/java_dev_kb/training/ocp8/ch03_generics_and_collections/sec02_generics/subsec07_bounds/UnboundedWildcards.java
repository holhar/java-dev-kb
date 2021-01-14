package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec02_generics.subsec07_bounds;

import java.util.ArrayList;
import java.util.List;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class UnboundedWildcards {

    public static void printListWithoutWildcards(List<Object> list) {
        for (Object x : list)
            println(x);
    }

    public static void printListWithWildcards(List<?> list) {
        for (Object x : list)
            println(x);
    }

    public static void main(String[] args) {
        List<String> keywords = new ArrayList<>();
        keywords.add("java");
        // printListWithoutWildcards(keywords); // DOES NOT COMPILE - String is no Object
        printListWithWildcards(keywords); // ...but with an unbounded wildcard that's no problem

        // Without wildcards you can not assign deviating types
        List<Integer> numbers = new ArrayList<>();
        numbers.add(new Integer(42));
        // List<Object> objects = numbers; // DOES NOT COMPILE - This is not allowed as we could mix types...
        // objects.add("forty two");
        // print(numbers.get(1));

        // On the other hand it's possible to assign an Integer array to an Object array
        Integer[] numbers2 = {new Integer(42)};
        Object[] objects = numbers2;
        // But java still knows later that the references should be Integers...
        objects[0] = "forty two";   // Throws ArrayStoreException
    }

}
