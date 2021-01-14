package de.holhar.java_dev_kb.training.ocp8.utils;

public class OcpPrepUtils {

    private OcpPrepUtils() {
        throw new UnsupportedOperationException("Utils class with static methods only");
    }

    public static void println(Object o) {
        System.out.println(o);
    }

    public static void print(Object o) {
        System.out.print(o);
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
