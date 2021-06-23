package de.holhar.java_dev_kb.training.concurrency.ch02_thread_safety.s3_locking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Code that would deadlock if intrinsic locks were not reentrant.
 */
public class LoggingWidget extends Widget {

    public static final Logger LOGGER = LoggerFactory.getLogger(LoggingWidget.class);

    @Override
    public synchronized void doSomething() {
        LOGGER.debug("{}: calling doSomething", toString());
        super.doSomething();
    }
}

class Widget {
    public synchronized void doSomething() {
        // ...
    }
}