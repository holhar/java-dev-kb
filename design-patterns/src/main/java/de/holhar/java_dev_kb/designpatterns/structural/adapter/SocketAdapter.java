package de.holhar.java_dev_kb.designpatterns.structural.adapter;

public interface SocketAdapter {
    Volt get120Volt();
    Volt get12Volt();
    Volt get3Volt();
}
