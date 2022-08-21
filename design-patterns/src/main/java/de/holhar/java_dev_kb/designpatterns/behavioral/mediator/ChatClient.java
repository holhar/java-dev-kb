package de.holhar.java_dev_kb.designpatterns.behavioral.mediator;

public class ChatClient {

    public static void main(String[] args) {
        ChatMediatorImpl mediator = new ChatMediatorImpl();
        UserImpl user1 = new UserImpl(mediator, "Paul");
        UserImpl user2 = new UserImpl(mediator, "John");
        UserImpl user3 = new UserImpl(mediator, "George");
        UserImpl user4 = new UserImpl(mediator, "Ringo");
        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);
        mediator.addUser(user4);
        user1.send("Hi all!");
    }
}
