package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec02_generics.subsec03_generic_classes;

/**
 * @author hhs@dasburo.com
 */
public class Zoo {

    public static void main(String[] args) {
        Elephant elephant = new Elephant();
        Crate<Elephant> createForElephant = new Crate<>();
        createForElephant.packCreate(elephant);
        Elephant inNewHome = createForElephant.emptyCrate();
    }
}

class Elephant {
}