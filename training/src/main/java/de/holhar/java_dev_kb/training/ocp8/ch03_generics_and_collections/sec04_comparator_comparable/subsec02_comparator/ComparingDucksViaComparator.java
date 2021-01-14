package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec04_comparator_comparable.subsec02_comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * @author hhs@dasburo.com
 */
public class ComparingDucksViaComparator {

    public static void main(String[] args) {

        List<Duck> ducks = new ArrayList<>();
        ducks.add(new Duck("Quack", 7));
        ducks.add(new Duck("Puddles", 10));

        Collections.sort(ducks);
        println(ducks);

        Collections.sort(ducks, byWeightLazy);
        println(ducks);
    }

    private static Comparator<Duck> byWeightLazy = Comparator.comparingInt(Duck::getWeight);

    private static Comparator<Duck> byWeightLambdaVeryShort = (d1, d2) -> d1.getWeight() - d2.getWeight();

    private static Comparator<Duck> byWeightLambdaShort = (Duck d1, Duck d2) -> d1.getWeight() - d2.getWeight();

    private static Comparator<Duck> byWeightLambdaMedium = (d1, d2) -> { return d1.getWeight() - d2.getWeight(); };

    private static Comparator<Duck> byWeightLambdaLong = (Duck d1, Duck d2) -> {return d1.getWeight() - d2.getWeight(); };

    private static Comparator<Duck> byWeightOldFashined = new Comparator<Duck>() {
        @Override
        public int compare(Duck d1, Duck d2) {
            return d1.getWeight() - d2.getWeight();
        }
    };
}
