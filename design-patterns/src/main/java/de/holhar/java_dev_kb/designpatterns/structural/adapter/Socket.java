package de.holhar.java_dev_kb.designpatterns.structural.adapter;

public class Socket {

    public Volt getVolt() {
        return new Volt(120);
    }
}
