package de.holhar.java_dev_kb.designpatterns.behavioral.mediator;

public interface ChatMediator {
    void sendMessage(String msg, User user);
    void addUser(User user);
}
