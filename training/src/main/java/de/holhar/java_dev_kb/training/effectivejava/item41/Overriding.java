package de.holhar.java_dev_kb.training.effectivejava.item41;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Wine {
    String name() {
        return "wine";
    }
}

class SparklingWine extends Wine {
    @Override
    String name() {
        return "sparkling wine";
    }
}

class Champagne extends SparklingWine {
    @Override
    String name() {
        return "champagne";
    }
}

public class Overriding {

    private static final Logger LOGGER = LoggerFactory.getLogger(Overriding.class);

    public static void main(String[] args) {
        Wine[] wines = {
                new Wine(), new SparklingWine(), new Champagne()
        };

        for (Wine wine : wines) {
            LOGGER.debug(wine.name());
        }
    }
}
