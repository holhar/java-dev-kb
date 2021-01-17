package de.holhar.java_dev_kb.training.ocp8.ch07_concurrency.sec05_parallel_streams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Stream;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class CreatingParallelStreams {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreatingParallelStreams.class);

    public static void main(String[] args) {

        // parallel()
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        Stream<Integer> parallelStream = stream.parallel();
        parallelStream.forEach(s -> LOGGER.debug("{}", s));

        println("");

        // parallelStream()
        Stream<Integer> parallelStream2 = Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream();
        parallelStream2.forEachOrdered(s -> LOGGER.debug("{}", s));
    }
}
