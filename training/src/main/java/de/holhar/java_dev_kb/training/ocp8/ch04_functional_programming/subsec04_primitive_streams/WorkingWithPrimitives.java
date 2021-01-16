package de.holhar.java_dev_kb.training.ocp8.ch04_functional_programming.subsec04_primitive_streams;

import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.function.BooleanSupplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class WorkingWithPrimitives {

    public static void main(String[] args) {

        // Calculating numbers in a finite stream
        Stream<Integer> s1 = Stream.of(1, 2, 3);
        println(s1.reduce(0, (n, s) -> n + s));

        // Another way of doing the same
        Stream<Integer> s2 = Stream.of(1, 2, 3);
        println(s2.mapToInt(x -> x).sum());

        // Creating primitive streams
        // ==========================

        IntStream s3 = IntStream.of(1, 2, 3);
        OptionalDouble opt1 = s3.average();
        println(opt1.getAsDouble());

        DoubleStream empty1 = DoubleStream.empty();
        DoubleStream one1 = DoubleStream.of(3.14);
        DoubleStream varargs1 = DoubleStream.of(1.0, 1.1, 1.2);

        println("");
        one1.forEach(System.out::println);
        println("");
        varargs1.forEach(System.out::println);

        DoubleStream random1 = DoubleStream.generate(Math::random);
        random1.limit(5).forEach(System.out::println);
        println("");
        DoubleStream fractions1 = DoubleStream.iterate(.5, d -> d / 2);
        fractions1.limit(3).forEach(System.out::println);
        println("");

        // Using ranges to count
        // so instead of this...
        IntStream.iterate(1, n -> n + 1).limit(6).forEach(System.out::print);
        println("");
        // We can do this...
        IntStream.range(1, 6).forEach(System.out::print);
        println("");
        // ...Or with closed range...
        IntStream.rangeClosed(1, 6).forEach(System.out::print);
        println("");

        // Creating primitive streams by mapping between different stream types
        Stream<String> objStream = Stream.of("penguin", "fish");
        IntStream s4 = objStream.mapToInt(String::length);

        // Using Optional with primitive streams
        IntStream s5 = IntStream.rangeClosed(1, 10);
        OptionalDouble opt2 = s5.average();
        opt2.ifPresent(System.out::println);
        println(opt2.getAsDouble());
        println(opt2.orElseGet(() -> Double.NaN));

        // Summarizing statistics
        IntStream numbers2 = IntStream.rangeClosed(25, 100);
        IntSummaryStatistics stats = numbers2.summaryStatistics();
        if (stats.getCount() == 0) {
            throw new RuntimeException();
        }
        println(stats.getMax() - stats.getMin());

        // Functional interfaces for boolean
        BooleanSupplier b1 = () -> true;
        BooleanSupplier b2 = () -> Math.random() > .5;
        println(b1.getAsBoolean());
        println(b2.getAsBoolean());
    }
}
