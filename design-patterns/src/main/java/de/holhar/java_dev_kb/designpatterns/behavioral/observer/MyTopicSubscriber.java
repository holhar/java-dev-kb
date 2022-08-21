package de.holhar.java_dev_kb.designpatterns.behavioral.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyTopicSubscriber implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(MyTopicSubscriber.class);

    private final String name;
    private Subject topic;

    public MyTopicSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update() {
        String message = (String) topic.getUpdate();
        if (message == null) {
            logger.info("'{}': no new message", this.name);
        } else {
            logger.info("'{}': Consuming message: '{}'", this.name, message);
        }
    }

    @Override
    public void setSubject(Subject subject) {
        this.topic = subject;
    }
}
