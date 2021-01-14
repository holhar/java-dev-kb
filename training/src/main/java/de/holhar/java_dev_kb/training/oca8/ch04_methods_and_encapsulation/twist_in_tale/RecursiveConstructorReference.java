package de.holhar.java_dev_kb.training.oca8.ch04_methods_and_encapsulation.twist_in_tale;

class Employee {
    String name;
    int age;

    public Employee() {
        // Does not compile - can not apply recursive constructor invocation
        // this();
    }

    public Employee(String newName, int newAge) {
        name = newName;
        age = newAge;
    }
}
