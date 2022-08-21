package de.holhar.java_dev_kb.designpatterns.behavioral.mediator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserImpl extends User {

    private static final Logger logger = LoggerFactory.getLogger(UserImpl.class);

    public UserImpl(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String msg) {
        logger.info("{} - sending message: {}", this.name, msg);
        mediator.sendMessage(msg, this);
    }

    @Override
    public void receive(String msg) {
        logger.info("{} - received message: {}", this.name, msg);
    }
}
