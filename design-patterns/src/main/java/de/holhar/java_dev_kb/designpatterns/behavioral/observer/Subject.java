package de.holhar.java_dev_kb.designpatterns.behavioral.observer;

public interface Subject {
    void register(Observer observer);
    void unregister(Observer observer);

    void notifyObservers();

    // Get state updates from subjects
    Object getUpdate();
}
