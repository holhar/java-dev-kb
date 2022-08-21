package de.holhar.java_dev_kb.designpatterns.behavioral.observer;

public class ObserverPatternClient {

    public static void main(String[] args) {
        // Create subject
        MyTopic topic = new MyTopic();

        // Create observers
        MyTopicSubscriber observer1 = new MyTopicSubscriber("Observer 1");
        MyTopicSubscriber observer2 = new MyTopicSubscriber("Observer 2");
        MyTopicSubscriber observer3 = new MyTopicSubscriber("Observer 3");

        // Register observers to the subject
        topic.register(observer1);
        topic.register(observer2);
        topic.register(observer3);

        // Attach subject to observers
        observer1.setSubject(topic);
        observer2.setSubject(topic);
        observer3.setSubject(topic);

        // Check if any update is available
        observer1.update();

        // Send message to subject
        topic.postMessage("Hello world!");
    }
}
