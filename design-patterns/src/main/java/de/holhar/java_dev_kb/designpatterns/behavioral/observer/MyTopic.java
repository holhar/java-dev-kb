package de.holhar.java_dev_kb.designpatterns.behavioral.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MyTopic implements Subject {

    private static final Logger logger = LoggerFactory.getLogger(MyTopic.class);

    private final List<Observer> observers;
    private String message;
    private boolean changed;
    private final Object MUTEX = this;

    public MyTopic() {
        this.observers = new ArrayList<>();
    }

    // Method to post message to the topic
    public void postMessage(String message) {
        logger.info("Message posted to topic: '{}'", message);
        this.message = message;
        this.changed = true;
        notifyObservers();
    }


    @Override
    public void register(Observer observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Null reference given for observer");
        }

        synchronized (MUTEX) {
            if (!observers.contains(observer)) {
                observers.add(observer);
            }
        }
    }

    @Override
    public void unregister(Observer observer) {
        synchronized (MUTEX) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        List<Observer> observersLocal = null;
        // Synchronization is used to make sure any observer registered after message is received, is not notified
        synchronized (MUTEX) {
            if (!changed) {
                return;
            }
            observersLocal = new ArrayList<>(this.observers);
            this.changed = false;
        }
        for (Observer o : observersLocal) {
            o.update();
        }
    }

    @Override
    public Object getUpdate() {
        return this.message;
    }
}
