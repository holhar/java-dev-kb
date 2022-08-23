package de.holhar.java_dev_kb.designpatterns.behavioral.memento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Make an object (originator) itself responsible for:
 * 1. Saving its internal state to a (memento) object and
 * 2. Restoring to a previous state from a (memento) object.
 * 3. Only the originator that created a memento is allowed to access it.
 */
public class Originator {

    private static final Logger logger = LoggerFactory.getLogger(Originator.class);

    private int stateId;

    public Originator() {
        this.stateId = 0;
        logger.info("Originator is created with state id '{}'", stateId);
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        logger.info("Setting the state id of the originator to '{}'", stateId);
        this.stateId = stateId;
    }

    // Saving its internal state to a (memento) object
    public Memento saveMemento() {
        logger.info("Saving originator's current state id.");
        // Create memento with the current state and return it.
        return new Memento(stateId);
    }

    // Restoring to a previous state from a (memento) object.
    public void revertMemento(Memento previousMemento) {
        logger.info("Restoring to state id '{}'", previousMemento.getStateId());
        this.stateId = previousMemento.getStateId();
        logger.info("Current state id of originator: '{}'", stateId);
    }
}
