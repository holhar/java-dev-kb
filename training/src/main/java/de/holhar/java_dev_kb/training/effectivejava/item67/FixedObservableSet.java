package de.holhar.java_dev_kb.training.effectivejava.item67;

import de.holhar.java_dev_kb.training.effectivejava.item16.ForwardingSet;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class FixedObservableSet<E> extends ForwardingSet<E> {

    public FixedObservableSet(Set<E> set) {
        super(set);
    }

    // Thread-safe observable set with CopyOnWriteArrayList
    private final List<FixedSetObserver<E>> observers = new CopyOnWriteArrayList<FixedSetObserver<E>>();

    public void addObserver(FixedSetObserver<E> observer) {
        observers.add(observer);
    }

    public boolean removeObserver(FixedSetObserver<E> observer) {
        return observers.remove(observer);
    }

    private void notifyElementAdded(E element) {
        for (FixedSetObserver<E> observer : observers)
            observer.added(this, element);
    }

    /*
    Alien method moved outside of synchronized block - open calls
    private void notifyElementAdded(E element) {
        List<FixedFixedSetObserver<E>> snapshot = null;
        synchronized(observers) {
            snapshot = new ArrayList<FixedFixedSetObserver<E>>(observers);
        }
        for (FixedFixedSetObserver<E> observer : snapshot)
            observer.added(this, element);
    }
    */

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
