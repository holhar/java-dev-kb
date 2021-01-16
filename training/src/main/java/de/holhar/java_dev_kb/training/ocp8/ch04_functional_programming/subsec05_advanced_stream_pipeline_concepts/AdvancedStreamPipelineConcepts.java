package de.holhar.java_dev_kb.training.ocp8.ch04_functional_programming.subsec05_advanced_stream_pipeline_concepts;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class AdvancedStreamPipelineConcepts {

    public static void main(String[] args) {

        // Chaining Optionals
        // ==================

        Optional<Integer> optional1 = Optional.of(123);
        // old-fashioned, not functional way;
        if (optional1.isPresent()) {
            Integer num = optional1.get();
            String string = "" + num;
            if (string.length() == 3)
                println(string);
        }
        // new cool functional way:
        optional1.map(number -> "" + number)
                .filter(string -> string.length() == 3)
                .ifPresent(System.out::println);

        // Suppose that we wanted to get an Optional<Integer> representing the length of the String contained in another Optional:
        Optional<String> optional2 = Optional.of("blub");
        Optional<Integer> optional3 = optional2.map(String::length);

        // What if we had a helper method that did the logic of calculating something for us and it had the signature
        // Optional<Integer> calculator(String s)?
        // optional3 = optional2.map(AdvancedStreamPipelineConcepts::calculator); // DOES NOT COMPILE
        // The problem is that calculator return Optional<Integer> - the map() method add another Optional, giving us
        // Optional<Optional<Integer>> -> the solution is to call flatMap()
        optional3 = optional2.flatMap(AdvancedStreamPipelineConcepts::calculator);

        // Collecting results
        // ==================

        // Basic collectors

        String result1 = getStream().collect(Collectors.joining(", "));
        println(result1);

        Double result2 = getStream().collect(Collectors.averagingInt(String::length));
        println(result2);

        TreeSet<String> result3 = getStream().filter(s -> s.startsWith("t"))
                .collect(Collectors.toCollection(TreeSet::new));
        println(result3);

        // Collecting into maps

        // need to provide two functions: one to define the key, one to define the value
        Map<String, Integer> result4 = getStream().collect(Collectors.toMap(s -> s, String::length));
        println(result4);

        // What happens if we reverse key and value of the previous example?
        Map<Integer, String> result5;
        try {
            result5 = getStream().collect(Collectors.toMap(String::length, s -> s)); // Throws exception
        } catch (IllegalStateException e) {
            println("Exception thrown due to duplicate key (the collector does not know what to do)");
        }
        // Solution: provide a merge function:
        result5 = getStream().collect(Collectors.toMap(String::length, s -> s, (x1, x2) -> x1 + ", " + x2));
        println(result5);
        // Collects creating a HashMap:
        println(result5.getClass());
        // If we want to have another collection type:
        Map<Integer, String> result6 = getStream()
                .collect(Collectors.toMap(String::length, s -> s, (x1, x2) -> x1 + ", " + x2, TreeMap::new));
        println(result6);

        // Collecting using Grouping, Partitioning, and Mapping
        // Grouping

        Map<Integer, List<String>> result7 = getStream().collect(Collectors.groupingBy(String::length));
        println(result7);

        // Do the same thing, but return a set as value of the map
        Map<Integer, Set<String>> result8 = getStream().collect(Collectors.groupingBy(String::length, Collectors.toSet()));
        println(result8);

        // Provide specific type of set to be returned
        TreeMap<Integer, Set<String>> result9 = getStream().collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toSet()));
        println(result9);

        // Return a treeMap, but leave the value as list
        TreeMap<Integer, List<String>> result10 = getStream().collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toList()));
        println(result10);

        // Partioning

        Map<Boolean, List<String>> result11 = getStream().collect(Collectors.partitioningBy(s -> s.length() < 5));
        println(result11);

        Map<Boolean, List<String>> result12 = getStream().collect(Collectors.partitioningBy(s -> s.length() < 7));
        println(result12);

        // do the same, but with the value of the map as set
        Map<Boolean, Set<String>> result13 = getStream().collect(Collectors.partitioningBy(s -> s.length() < 7, Collectors.toSet()));
        println(result13);

        // map the lengths of animal name to the count they appear
        Map<Integer, Long> result14 = getStream().collect(Collectors.groupingBy(String::length, Collectors.counting()));
        println(result14);

        // FIXME: Does not work...
        // suppose that we wanted to get the first letter of the first animal alphabetically of each length
        //Map<Integer, Optional<Character>> result15 = getStream()
        //        .collect(Collectors.groupingBy(String::length, Collectors.mapping(s -> s.charAt(0), Collectors.minBy(Comparator.naturalOrder()))));
        //print(result15);

        // FIXME: Does not work...
        // do the same as in the previous example, but leave out the classes of the static methods
        //Map<Integer, Optional<Character>> result16 = getStream()
        //        .collect(Collectors.groupingBy(String::length, mapping(s -> s.charAt(0), minBy(Comparator.naturalOrder()))));
        //print(result16);
    }

    private static Optional<Integer> calculator(String s) {
        return Optional.of(s.length());
    }

    private static Stream<String> getStream() {
        return Stream.of("lions", "tigers", "bears");
    }
}
