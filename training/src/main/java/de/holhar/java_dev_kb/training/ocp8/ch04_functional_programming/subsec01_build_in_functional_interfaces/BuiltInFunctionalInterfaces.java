package de.holhar.java_dev_kb.training.ocp8.ch04_functional_programming.subsec01_build_in_functional_interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class BuiltInFunctionalInterfaces {

    public static void main(String[] args) {

        // Implementing Supplier - is used when you want to generate or supply values without taking any input
        Supplier<StringBuilder> s1 = StringBuilder::new;
        Supplier<StringBuilder> s2 = () -> new StringBuilder();
        println(s1.get());
        println(s2.get());

        Supplier<ArrayList<String>> s3 = ArrayList<String>::new;
        List<String> a1 = s3.get();
        println(a1);

        // Implementing Consumer and BiConsumer - used when you want to do something with a parameter but not return anything
        Consumer<String> c1 = System.out::println;
        Consumer<String> c2 = x -> System.out.println(x);
        c1.accept("Annie");
        c2.accept("Annie");

        Map<String, Integer> map = new HashMap<>();
        BiConsumer<String, Integer> b1 = map::put;
        BiConsumer<String, Integer> b2 = (k, v) -> map.put(k, v);

        b1.accept("chicken", 7);
        b2.accept("chick", 1);
        println(map);

        // Implementing Predicate and BiPredicate - often used when filtering or matching
        Predicate<String> p1 = String::isEmpty;
        Predicate<String> p2 = value -> value.isEmpty();
        println(p1.test(""));
        println(p2.test(""));

        BiPredicate<String, String> p3 = String::startsWith;
        BiPredicate<String, String> p4 = (string, prefix) -> string.startsWith(prefix);
        println(p3.test("chicken", "chick"));
        println(p4.test("chicken", "chick"));

        // Predicates can be concatenated
        Predicate<String> egg = s -> s.contains("egg");
        Predicate<String> brown = s -> s.contains("brown");
        Predicate<String> brownEggs = egg.and(brown);
        Predicate<String> otherEggs = egg.and(brown.negate());
        println(brownEggs.test("a very brown egg"));
        println(otherEggs.test("a very brown egg"));

        // Implementing Function and BiFunction - responsible for turning one or two parameters of some values into a
        //                                        potentially different type and returning it.
        Function<String, Integer> f1 = String::length;
        Function<String, Integer> f2 = s -> s.length();
        println(f1.apply("blibediblablub"));
        println(f2.apply("blibediblablub"));

        BiFunction<String, String, String> f3 = String::concat;
        BiFunction<String, String, String> f4 = (x1, x2) -> x1.concat(x2);
        println(f3.apply("awesome ", "stuff"));
        println(f4.apply("awesome ", "stuff"));

        // Implementing UnaryOperator and BinaryOperator - special  type of a function by handling only one specific type
        UnaryOperator<String> o1 = String::toUpperCase;
        UnaryOperator<String> o2 = s -> s.toUpperCase();
        println(o1.apply("chirp"));
        println(o2.apply("chirp"));

        BinaryOperator<String> o3 = String::concat;
        BinaryOperator<String> o4 = (y1, y2) -> y1.concat(y2);
        println(o3.apply("baby ", "chirp"));
        println(o4.apply("baby ", "chirp"));
    }
}
