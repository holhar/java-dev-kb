package de.holhar.java_dev_kb.katas.ctci.chapters.ch08.two;

public class Call {

    private static int id;
    private boolean dispatched;

    public Call() {
        id++;
        this.dispatched = false;
    }

    public void setDispatched(boolean dispatched) {
        this.dispatched = dispatched;
    }
}
