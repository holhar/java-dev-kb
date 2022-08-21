package de.holhar.java_dev_kb.designpatterns.behavioral.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WindowsFileSystemReceiver implements FileSystemReceiver {
    
    private static final Logger logger = LoggerFactory.getLogger(WindowsFileSystemReceiver.class);

    @Override
    public void openFile() {
        logger.info("Opening file in Windows OS");
    }

    @Override
    public void writeFile() {
        logger.info("Writing file in Windows OS");
    }

    @Override
    public void closeFile() {
        logger.info("Closing file in Windows OS");
    }
}
