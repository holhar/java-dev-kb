package de.holhar.java_dev_kb.training.ocp8.ch08_io.sec03_streams_usage.subsec04_objectinputstream_objectoutputstream;

import java.io.Serializable;

public class Animal2 implements Serializable {

    private static final long serialVersionUID = 2L;
    private static char type = 'C';
    /*
     * Transient and static properties will not get serialized!
     */
    private transient String name;
    private transient int age = 10;

    {
        this.age = 14;
    }

    public Animal2() {
        this.name = "Unknown";
        this.age = 12;
        this.type = 'Q';
    }

    public Animal2(String name, int age, char type) {
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
