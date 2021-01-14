package de.holhar.java_dev_kb.training.oca8.ch02_operators_and_statements.twist_in_tale;

public class LogicalOperators {

    public static void main(String[] args) {
        int a = 10, b = 20, c = 40;

        System.out.println(a++ > 10 || ++b < 30); // true: both execute
        System.out.println(a > 90 && ++b < 30); // false: only first executes
        System.out.println(!(c > 20) && a == 10); // false: only first executes
        System.out.println(a >= 99 || a <= 33 && b == 10); // false: all execute
        System.out.println(a >= 99 && a <= 33 || b == 10); // false: first and last execute
    }
}
