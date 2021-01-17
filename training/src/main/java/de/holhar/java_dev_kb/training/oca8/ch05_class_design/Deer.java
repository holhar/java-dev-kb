package de.holhar.java_dev_kb.training.oca8.ch05_class_design;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Deer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Deer.class);

    public Deer() {
        LOGGER.debug("Deer");
    }

    public Deer(int age) {
        LOGGER.debug("DeerAge");
    }

    public static void main(String[] args) {
        Deer deer = new Reindeer(5);
        // As Deer is the reference class, the hidden 'hasHorns()' gets triggered.
        LOGGER.debug(",{}", deer.hasHorns());
        // => output is 'DeerReindeer,false'
    }

    // Private method gets hidden, not overridden
    private boolean hasHorns() {
        return false;
    }
}

class Reindeer extends Deer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Reindeer.class);

    public Reindeer(int age) {
        // Since there is no explicit call to the parent constructor,
        // the default no-argument 'super()' is inserted as the first
        // line of the constructor.
        LOGGER.debug("Reindeer");
    }

    public boolean hasHorns() {
        return true;
    }
}
