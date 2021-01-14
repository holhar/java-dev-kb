package de.holhar.java_dev_kb.training.oca8.ch02_operators_and_statements.twist_in_tale;

import static de.holhar.java_dev_kb.training.oca8.util.Oca8Utils.print;

public class UnaryOperators {

    public static void main(String[] args) {
        int a = 10;
        a = ++a + a + --a - --a + a++;
        print(a); // a = 32
    }
}
