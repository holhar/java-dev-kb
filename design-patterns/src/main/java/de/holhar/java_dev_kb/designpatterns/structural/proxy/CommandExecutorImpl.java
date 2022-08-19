package de.holhar.java_dev_kb.designpatterns.structural.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CommandExecutorImpl implements CommandExecutor {

    private static final Logger logger = LoggerFactory.getLogger(CommandExecutorImpl.class);

    @Override
    public void runCommand(String cmd) throws IOException {
        // Some heave implementation
        Runtime.getRuntime().exec(cmd);
        logger.info("'{}' command executed", cmd);
    }
}
