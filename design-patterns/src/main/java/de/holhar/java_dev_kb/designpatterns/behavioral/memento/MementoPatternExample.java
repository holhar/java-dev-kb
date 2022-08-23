package de.holhar.java_dev_kb.designpatterns.behavioral.memento;

public class MementoPatternExample {

    public static void main(String[] args) {
        Originator originator = new Originator();
        originator.setStateId(1);

        // A client (caretaker) can request a memento from the originator to save the internal state of the originator
        Memento memento = originator.saveMemento();

        originator.setStateId(2);
        memento = originator.saveMemento();

        originator.setStateId(3);
        originator.revertMemento(memento);
    }
}
