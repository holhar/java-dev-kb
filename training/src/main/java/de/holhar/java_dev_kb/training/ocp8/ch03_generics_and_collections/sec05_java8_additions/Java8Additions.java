package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec05_java8_additions;


import de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec04_comparator_comparable.subsec02_comparator.Duck;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * @author hhs@dasburo.com
 */
public class Java8Additions {

    /*
     * Using Method References
     */
    static class DuckHelper {
        public static int compareByWeight(Duck d1, Duck d2) {
            return d1.getWeight() - d2.getWeight();
        }
        public static int compareByName(Duck d1, Duck d2) {
            return d1.getName().compareTo(d2.getName());
        }
    }

    // Method reference:
    Comparator<Duck> byWeight = DuckHelper::compareByWeight;

    // Old fashioned way:
    Comparator<Duck> byWeightOld = (d1, d2) -> DuckHelper.compareByWeight(d1, d2);


    public static void main(String[] args) {

        /*
         * Removing Conditionally
         */
        List<String> list = new ArrayList<>();
        list.add("Magician");
        list.add("Assistant");
        println(list);
        list.removeIf(item -> item.startsWith("A"));
        println(list);

        /*
         * Updating all elements
         */
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        numbers.replaceAll(x -> x * x);
        println(numbers);

        /*
         * Using New Java 8 Map APIs
         * =========================
         */

        /*
         * merge
         */
        // BiFunction - Represents a function that accepts two arguments and produces a result
        BiFunction<String, String, String> mapper1 = (v1, v2) -> v1.length() > v2.length() ? v1 : v2;

        Map<String, String> favorites = new HashMap<>();
        favorites.put("Jenny", "Bus Tour");
        favorites.put("Tom", "Tram");

        String jenny = favorites.merge("Jenny", "Skyride", mapper1);
        String tom = favorites.merge("Tom", "Skyride", mapper1);

        println(favorites);
        println(jenny);
        println(tom);

        // The merge() method also has logic for what happens if nulls or missing keys are involved
        // In this case, it doesn't call the BiFunction at all, and it simply uses the new value
        favorites.put("Sam", null);
        favorites.merge("Sam", "Skyride", mapper1);
        println(favorites); // The mapping function isn't call - we would have a NPE, if it would have been

        // If the mapping function is called and returns null, the key is removed from the map:
        BiFunction<String, String, String> mapper2 = (v1, v2) -> null;

        favorites.merge("Jenny", "Skyride", mapper2); // Jenny gets removed
        favorites.merge("Rudolph", "Skyride", mapper2); // Sam gets added

        println(favorites);


        /*
         * computeIfPresent - calls the BiFunction if the requested key is found
         */
        BiFunction<String, Integer, Integer> mapper3 = (k, v) -> v + 1;

        Map<String, Integer> counts = new HashMap<>();
        counts.put("Jenny", 1);

        Integer jennyCount = counts.computeIfPresent("Jenny", mapper3);
        Integer samCount = counts.computeIfPresent("Sam", mapper3);

        println(counts); // {Jenny=2}
        println(jennyCount); // 2
        println(samCount); // null

        /*
         * computeIfAbsent - the functional interface only runs when the key isn't present or is null:
         */
        counts.put("Jenny", 15);
        counts.put("Tom", null);

        Function<String, Integer> mapper4 = (k) -> 1;
        jennyCount = counts.computeIfAbsent("Jenny", mapper4);
        samCount = counts.computeIfAbsent("Sam", mapper4);
        Integer tomCount = counts.computeIfAbsent("Tom", mapper4);
        println(counts); // {Tom=1, Jenny=15, Sam=1}

        // For computeIfPresent() the key is removed from the map, if the mapping function is called and returns null
        // For computeIfAbsent(), the key is never added to the map in the first place:
        counts.computeIfPresent("Jenny", (k, v) -> null); // Jenny will be removed
        counts.computeIfAbsent("Larry", k -> null);       // Larry will not be added
        println(counts);
    }
}
