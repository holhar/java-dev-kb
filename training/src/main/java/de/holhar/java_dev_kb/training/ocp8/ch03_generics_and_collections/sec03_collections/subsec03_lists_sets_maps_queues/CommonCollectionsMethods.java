package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec03_collections.subsec03_lists_sets_maps_queues;

import java.util.*;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * @author hhs@dasburo.com
 */
public class CommonCollectionsMethods {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        println(list.add("Sparrow")); // true
        println(list.add("Sparrow")); // true

        // Common methods start
        println(list.remove("Sparrow")); // true
        println(list.isEmpty());
        println(list.size());
        println(list.contains("Sparrow")); // true
        list.clear();
        // Common methods end
    }
}
