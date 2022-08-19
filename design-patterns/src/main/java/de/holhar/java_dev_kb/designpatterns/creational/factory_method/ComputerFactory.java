package de.holhar.java_dev_kb.designpatterns.creational.factory_method;

public class ComputerFactory {

    private ComputerFactory() {
        throw new UnsupportedOperationException("Utils class - no instantiation available");
    }

    public static Computer getComputer(String type, String ram, String hdd, String cpu) {
        if ("PC".equalsIgnoreCase(type)) {
            return new PC(ram, hdd, cpu);
        } else if ("Server".equalsIgnoreCase(type)) {
            return new Server(ram, hdd, cpu);
        }
        return null;
    }
}
