package de.holhar.java_dev_kb.designpatterns.misc.dependency_injection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailService implements MessageService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Override
    public void sendMessage(String msg, String rec) {
        logger.info("Email sent to '{}' with message '{}'", rec, msg);
    }
}
