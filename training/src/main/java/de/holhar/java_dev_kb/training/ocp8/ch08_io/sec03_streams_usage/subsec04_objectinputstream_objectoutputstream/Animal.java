package de.holhar.java_dev_kb.training.ocp8.ch08_io.sec03_streams_usage.subsec04_objectinputstream_objectoutputstream;

import java.io.Serializable;

public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private char type;

    public Animal(String name, int age, char type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", type=" + type +
                '}';
    }
}
