package de.holhar.java_dev_kb.training.effectivejava.item67;

public interface BrokenSetObserver<E> {
    // Invoked when an element is added to the observable set
    void added(BrokenObservableSet<E> set, E element);
}
