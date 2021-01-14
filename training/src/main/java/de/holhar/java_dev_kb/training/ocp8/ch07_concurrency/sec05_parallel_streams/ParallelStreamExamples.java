package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec05_parallel_streams;

import de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class ParallelStreamExamples {

    public static void main(String[] args) {

        // Understanding independent operations
        // Return order of map function is FIFO, but the processing ordre may differ
        Arrays.asList("jackal", "kangaroo", "lemur")
                .parallelStream()
                .map(s -> {
                    println(s);
                    return s.toUpperCase();
                })
                .forEachOrdered(OcpPrepUtils::println);
        println("");

        // Avoiding stateful operations
        List<Integer> data = Collections.synchronizedList(new ArrayList<>());
        Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream()
                .map(i -> {
                    data.add(i);
                    return i;
                }) // AVOID STATEFUL LAMBDA EXPRESSIONS!
                .forEachOrdered(i -> System.out.print(i + " "));
        println("");
        for (Integer i : data) {
            System.out.print(i + " ");
        }
        println("");

        /*
         * Processing parallel reductions
         */

        // Performing order-based tasks
        // Result may be any number, depending on the first thread to finish the task
        println((Arrays.asList(1, 2, 3, 4, 5, 6)).parallelStream().findAny().get());

        // Creating unordered streams (may increase performance for parallel processing)
        Arrays.asList(1, 2, 3, 4, 5, 6).stream().unordered().parallel();
        println("");

        // Combining results with reduce()
        println(Arrays.asList('w', 'o', 'l', 'f').parallelStream().reduce("", (c, s1) -> c + s1, (s2, s3) -> s2 + s3));
        println("");

        // NOT AN ASSOCIATIVE ACCUMULATOR - using stream() outputs -21, using parallelStream() outputs 3
        println(Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().reduce(0, (a, b) -> (a - b)));
        // ALSO NOT ASSOCIATIVE
        println((Arrays.asList("w", "o", "l", "f")).parallelStream().reduce("X", (s1, s2) -> s1 + s2));
        println("");

        // Combining result with collect()
        Stream<String> stream1 = Stream.of("w", "o", "l", "f").parallel();
        SortedSet<String> set = stream1.collect(ConcurrentSkipListSet::new, Set::add, Set::addAll);
        println(set);

        Stream<String> stream2 = Stream.of("lions", "tigers", "bears").parallel();
        ConcurrentMap<Integer, String> ohMy1 = stream2
                .collect(Collectors.toConcurrentMap(String::length, k -> k, (s1, s2) -> s1 + "," + s2));
        println(ohMy1);

        Stream<String> stream3 = Stream.of("lions", "tigers", "bears").parallel();
        ConcurrentMap<Integer, List<String>> ohMy2 = stream3.collect(Collectors.groupingByConcurrent(String::length));
        println(ohMy2);
    }
}