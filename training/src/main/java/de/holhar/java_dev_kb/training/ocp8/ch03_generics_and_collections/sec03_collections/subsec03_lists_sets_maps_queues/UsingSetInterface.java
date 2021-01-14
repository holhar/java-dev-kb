package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec03_collections.subsec03_lists_sets_maps_queues;

import java.util.NavigableSet;
import java.util.TreeSet;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * @author hhs@dasburo.com
 */
public class UsingSetInterface {

    public static void main(String[] args) {
        NavigableSet<Integer> set = new TreeSet<>();
        set.add(5);
        set.add(9);
        set.add(10);
        set.add(19);
        set.add(20);
        set.remove(5);
        println(set);
        println(set.lower(10)); // 9
        println(set.floor(10)); // 10
        println(set.ceiling(20)); // 20
        println(set.higher(20)); // null
    }
}
