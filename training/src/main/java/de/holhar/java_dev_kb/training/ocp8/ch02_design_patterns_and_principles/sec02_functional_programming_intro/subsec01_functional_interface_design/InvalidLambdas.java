package de.holhar.java_dev_kb.training.ocp8.ch02_design_patterns_and_principles.sec02_functional_programming_intro.subsec01_functional_interface_design;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hhs@dasburo.com
 */
public class InvalidLambdas {

    private List<Duck> duckList = new ArrayList<>();

    public static void main(String[] args) {
        InvalidLambdas invalidLambdas = new InvalidLambdas();

        invalidLambdas.duckList.forEach(

                // DOES NOT COMPILE - no braces
                // Duck d -> d.quack()
                // a,d -> d.quack()
                // Animal a, Duck d -> d.quack

                // DOES NOT COMPILE
                // a, b -> a.startsWith("test") - no braces
                // c -> return 10;              - no curly braces
                // a -> { return a.startsWith("test") } - missing semicolon

                // DOES NOT COMPILE - missing types... either provide for both args or for no one
                // (int y, z) -> {int x=1; return y+10; }
                // (String s, z) -> { return s.length()+z; }
                // (a, Animal b, c) -> a.getName()

                // DOES NOT COMPILE - variable name is already defined as param
                // (a, b) -> {int a = 0; return 5;}

                Duck::quack
        );
    }
}

class Duck {

    public void quack() {
    }
}