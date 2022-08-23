package de.holhar.java_dev_kb.designpatterns.behavioral.memento;

public final class Memento {

    private final int stateId;

    public Memento(int stateId) {
        this.stateId = stateId;
    }

    public int getStateId() {
        return stateId;
    }
}
