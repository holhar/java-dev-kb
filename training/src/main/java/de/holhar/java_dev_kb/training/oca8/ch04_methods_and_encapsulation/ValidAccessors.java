package de.holhar.java_dev_kb.training.oca8.ch04_methods_and_encapsulation;

public class ValidAccessors {

    final private void method() {
    }

    // 'default' keyword is not allowed on classes other than interfaces
    // default private void method() {}

    // Java is case-sensitive, so this won't work:
    // Public void method() {}

    // Labels on methods are as well not allowed:
    // zzz: void method() {}
}
