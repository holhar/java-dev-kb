package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec05_parallel_streams;

import java.util.Arrays;
import java.util.stream.Stream;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class CreatingParallelStreams {

    public static void main(String[] args) {

        // parallel()
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        Stream<Integer> parallelStream = stream.parallel();
        parallelStream.forEach(s -> System.out.print(s + " "));

        println("");

        // parallelStream()
        Stream<Integer> parallelStream2 = Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream();
        parallelStream2.forEachOrdered(s -> System.out.print(s + " "));
    }
}
