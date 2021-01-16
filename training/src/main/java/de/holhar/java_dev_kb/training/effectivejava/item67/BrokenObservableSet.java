package de.holhar.java_dev_kb.training.effectivejava.item67;

import de.holhar.java_dev_kb.training.effectivejava.item16.ForwardingSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Broken - invokes alien method from synchronized block!
 */
public class BrokenObservableSet<E> extends ForwardingSet<E> {

    public BrokenObservableSet(Set<E> set) {
        super(set);
    }

    private final List<BrokenSetObserver<E>> observers = new ArrayList<BrokenSetObserver<E>>();

    public void addObserver(BrokenSetObserver<E> observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public boolean removeObserver(BrokenSetObserver<E> observer) {
        synchronized (observers) {
            return observers.remove(observer);
        }
    }

    private void notifyElementAdded(E element) {
        synchronized (observers) {
            for (BrokenSetObserver<E> observer : observers)
                observer.added(this, element);
        }
    }

    @Override
    public boolean add(E element) {
        boolean added = super.add(element);
        if (added)
            notifyElementAdded(element);
        return added;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E element : c)
            result |= add(element); // calls notifyElementAdded
        return result;
    }
}
