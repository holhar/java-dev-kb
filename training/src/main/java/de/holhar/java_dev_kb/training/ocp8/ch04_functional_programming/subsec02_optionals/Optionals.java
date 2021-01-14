package de.holhar.java_dev_kb.training.ocp8.ch04_functional_programming.subsec02_optionals;

import java.util.Optional;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * @author hhs@dasburo.com
 */
public class Optionals {

    public static void main(String[] args) {
        Optional<Double> opt1 = average(90, 100);

        // Checking if present
        if (opt1.isPresent()) {
            println(opt1.get());
        }

        // Doing the same thing using a consumer
        opt1.ifPresent(System.out::println);

        Optional<Double> opt2 = average();
        // Express what to do if the value is not present
        println(opt2.orElse(Double.NaN));
        println(opt2.orElseGet(() -> Math.random()));
        println(opt2.orElseThrow(() -> new IllegalArgumentException()));
    }

    private static Optional<Double> average(int... scores) {
        if (scores.length == 0)
            return Optional.empty();
        int sum = 0;
        for (int score : scores)
            sum += score;
        return Optional.of((double) sum / scores.length);
    }


}
