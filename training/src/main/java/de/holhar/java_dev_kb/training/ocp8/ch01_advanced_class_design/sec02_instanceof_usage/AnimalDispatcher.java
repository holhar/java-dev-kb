package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec02_instanceof_usage;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class AnimalDispatcher {

    public static void main(String[] args) {
        HeavyAnimal hippo = new Hippo();
        println(hippo instanceof Hippo);        // true - the hippo is clearly a hippo
        println(hippo instanceof HeavyAnimal);    // true - the hippo is a heavy animal
        println(hippo instanceof Elephant);        // false - the hippo is no elephant

        println(hippo instanceof Object);        // true - (almost) all objects inherit from Object
        Hippo nullHippo = null;
        println(nullHippo instanceof Object);    // false - for null references instanceof always returns false

        Hippo anotherHippo = new Hippo();
        //print(anotherHippo instanceof Elephant);	// DOES NOT COMPILE - there's no chance, that a Hippo variable
        // can be referencing an Elephant object, so the compiler complains

        // The compilation check only applies on classes - the next compiles:
        println(hippo instanceof Mother);
        // => because later there could be something like this:
        // 'class MotherHippo extends Hippo implements Mother { }'
    }
}
