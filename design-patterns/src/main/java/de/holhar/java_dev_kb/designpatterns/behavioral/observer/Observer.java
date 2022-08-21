package de.holhar.java_dev_kb.designpatterns.behavioral.observer;

public interface Observer {
    void update();
    void setSubject(Subject subject);
}
