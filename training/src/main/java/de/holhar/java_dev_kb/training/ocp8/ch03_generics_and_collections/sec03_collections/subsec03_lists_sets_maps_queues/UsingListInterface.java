package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec03_collections.subsec03_lists_sets_maps_queues;

import java.util.ArrayList;
import java.util.List;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class UsingListInterface {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Tony");
        list.add("Tony");
        list.add(0, "Sue");
        list.get(1); // Tony
        list.indexOf("Sue"); // 0
        list.lastIndexOf("Tony"); // 2
        list.remove(0);
        list.set(1, "Betsy");
        println(list);
    }
}
