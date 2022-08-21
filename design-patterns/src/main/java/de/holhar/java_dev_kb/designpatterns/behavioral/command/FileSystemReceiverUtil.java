package de.holhar.java_dev_kb.designpatterns.behavioral.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSystemReceiverUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileSystemReceiverUtil.class);

    private FileSystemReceiverUtil() {
        throw new UnsupportedOperationException("Util class - do not instantiate.");
    }

    public static FileSystemReceiver getUnderlyingFileSystem() {
        String osName = System.getProperty("os.name");
        logger.info("Underlying OS is '{}'", osName);
        if (osName.contains("Windows")) {
            return new WindowsFileSystemReceiver();
        } else {
            return new UnixFileSystemReceiver();
        }
    }
}
