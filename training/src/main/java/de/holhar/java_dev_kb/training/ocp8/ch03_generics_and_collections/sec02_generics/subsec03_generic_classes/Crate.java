package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec02_generics.subsec03_generic_classes;

/**
 * @author hhs@dasburo.com
 */
public class Crate<T> {

    private T contents;

    public T emptyCrate() {
        return contents;
    }

    public void packCreate(T contents) {
        this.contents = contents;
    }
}
