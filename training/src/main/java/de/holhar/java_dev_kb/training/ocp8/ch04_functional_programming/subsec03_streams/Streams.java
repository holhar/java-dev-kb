package de.holhar.java_dev_kb.training.ocp8.ch04_functional_programming.subsec03_streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * @author hhs@dasburo.com
 */
public class Streams {

    public static void main(String[] args) {

        // Creating streams sources
        Stream<String> empty1 = Stream.empty();
        Stream<Integer> singleElement1 = Stream.of(1);
        Stream<Integer> fromArray1 = Stream.of(1, 2, 3);

        List<String> list1 = Arrays.asList("a", "b", "c");
        Stream<String> fromList = list1.stream();
        Stream<String> fromListParallel = list1.parallelStream();

        // Create infinite streams
        Stream<Double> randoms = Stream.generate(Math::random);
        Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);

        // Using common terminal operations
        // ================================

        // count()
        Stream<String> s1 = getSimpleStream();
        println(s1.count());

        // min() / max()
        Stream<String> s2 = getSimpleStream();
        Optional<String> min = s2.min((x1, x2) -> x1.length() - x2.length());
        min.ifPresent(System.out::println);

        Optional<?> minEmpty = Stream.empty().min((x1, x2) -> 0);
        println(minEmpty.isPresent());

        // findAny() / findFirst()
        Stream<String> s3 = getSimpleStream();
        Stream<String> infinite1 = Stream.generate(() -> "chimp");
        s3.findFirst().ifPresent(System.out::println);
        infinite1.findAny().ifPresent(System.out::println);

        // allMatch() / anyMatch() / noneMatch()
        List<String> list2 = Arrays.asList("monkey", "2", "chimp");
        Stream<String> infinite2 = Stream.generate(() -> "chimp");
        Predicate<String> startsWithLetter = x -> Character.isLetter(x.charAt(0));
        println(list2.stream().allMatch(startsWithLetter));
        println(list2.stream().anyMatch(startsWithLetter));
        println(list2.stream().noneMatch(startsWithLetter));
        // can be used with an infinite stream
        println(infinite2.anyMatch(startsWithLetter));

        // forEach()
        Stream<String> s4 = getSimpleStream();
        s4.forEach(System.out::print);

        // reduce() - combines a stream into a single object
        // old-fashioned way
        String[] array = new String[]{"w", "o", "l", "f"};
        String result = "";
        for (String letter : array) {
            result += letter;
        }
        println(result);

        // reduce using identity and accumulator
        Stream<String> s5 = getWolfStream();
        String word1 = s5.reduce("", (x1, x2) -> x1 + x2);
        println(word1);

        // doing the same with method reference
        Stream<String> s6 = getWolfStream();
        String word2 = s6.reduce("", String::concat);
        println(word2);

        // reduce using an accumulator only, returning an optional
        BinaryOperator<Integer> op1 = (x1, x2) -> x1 * x2;
        Stream<Integer> empty2 = Stream.empty();
        Stream<Integer> singleElement2 = Stream.of(3);
        Stream<Integer> fromArray2 = Stream.of(1, 2, 3);

        empty2.reduce(op1).ifPresent(System.out::println);
        singleElement2.reduce(op1).ifPresent(System.out::println);
        fromArray2.reduce(op1).ifPresent(System.out::println);

        // reduce using identity, accumulator, and combiner - used when we are processing collections in parallel.
        //                      It allows Java to create intermediate reductions and then combine them at the end
        BinaryOperator<Integer> op2 = (y1, y2) -> y1 * y2;
        Stream<Integer> numbers1 = Stream.of(4, 6, 9);
        numbers1.reduce(1, op2, op2);

        // collect()
        // collect using supplier, accumulator, and combiner
        Stream<String> s7 = getWolfStream();
        StringBuilder word3 = s7.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
        println(word3);

        // collect using supplier, accumulator, and combiner with different logic than in accumulator
        Stream<String> s8 = getWolfStream();
        TreeSet<String> set1 = s8.collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
        println(set1);

        // collect using provided Collectors
        Stream<String> s9 = getWolfStream();
        TreeSet<String> set2 = s9.collect(Collectors.toCollection(TreeSet::new));
        println(set2);
        // ...even shorter:
        Stream<String> s10 = getWolfStream();
        Set<String> set3 = s10.collect(Collectors.toSet());
        println(set3);

        // Using common intermediate operations
        // ====================================

        // filter()
        Stream<String> s11 = getSimpleStream();
        s11.filter(s -> s.startsWith("m")).forEach(System.out::println);

        // distinct() - removes duplicate values
        Stream<String> s12 = Stream.of("duck", "duck", "duck", "goose");
        s12.distinct().forEach(System.out::print);

        // limit() / skip()
        Stream<Integer> infinite3 = Stream.iterate(1, n -> n + 1);
        infinite3.skip(13).limit(5).forEach(System.out::print);

        // map()
        Stream<String> s13 = getSimpleStream();
        s13.map(String::length).forEach(System.out::print);

        // flatMap() - takes each element in the stream and makes any elements it contains top-level
        //             elements in a single stream
        List<String> zero = Arrays.asList();
        List<String> one = Arrays.asList("Bonobo");
        List<String> two = Arrays.asList("Mama Gorilla", "Baby Gorilla");
        Stream<List<String>> animals = Stream.of(zero, one, two);
        animals.flatMap(l -> l.stream()).forEach(System.out::println);

        // sorted() - Java uses natural ordering unless we specify a comparator
        Stream<String> s14 = Stream.of("brown-", "bear-");
        s14.sorted().forEach(System.out::println);

        Stream<String> s15 = Stream.of("brown bear-", "grizzly-");
        //s15.sorted(Comparator::reverseOrder); // DOES NOT COMPILE - reverseOrder return type is not compatible with interface
        s15.sorted(Comparator.reverseOrder()).forEach(System.out::println);

        // peek()
        Stream<String> s16 = getSimpleStream();
        // beware of changing state with peek
        long count = s16.filter(s -> s.startsWith("b")).peek(System.out::println).count();
        println(count);

        // hanging streams
        /*
        Stream.generate(() -> "blub")
                .filter(s -> s.length() == 4)
                .sorted()
                .limit(2)
                .forEach(System.out::println);
        */
    }

    private static Stream<String> getSimpleStream() {
        return Stream.of("monkey", "gorilla", "bonobo");
    }

    private static Stream<String> getWolfStream() {
        return Stream.of("w", "o", "l", "f");
    }
}
