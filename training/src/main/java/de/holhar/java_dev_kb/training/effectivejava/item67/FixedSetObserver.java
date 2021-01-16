package de.holhar.java_dev_kb.training.effectivejava.item67;

public interface FixedSetObserver<E> {
    // Invoked when an element is added to the observable set
    void added(FixedObservableSet<E> set, E element);
}
