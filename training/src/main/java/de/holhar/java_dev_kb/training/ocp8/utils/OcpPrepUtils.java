package de.holhar.java_dev_kb.training.ocp8.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OcpPrepUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(OcpPrepUtils.class);

    private OcpPrepUtils() {
        throw new UnsupportedOperationException("Utils class with static methods only");
    }

    public static void println(Object o) {
        LOGGER.debug("{}", o);
    }

    public static String getIoHome() {
        return "src/main/resources/io-examples/";
    }

    public static String getNioHome() {
        return "src/main/resources/nio-examples/";
    }

    public static String getZooDbBaseUrl() {
        return "jdbc:derby:zoo";
    }
}
