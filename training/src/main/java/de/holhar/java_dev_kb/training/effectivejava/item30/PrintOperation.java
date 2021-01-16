package de.holhar.java_dev_kb.training.effectivejava.item30;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintOperation {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintOperation.class);

    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        for (Operation op : Operation.values()) {
            LOGGER.debug("{} {} {} = {}\n", x, op, y, op.apply(x, y));
        }

        LOGGER.debug("Using the fromString-method: {}", Operation.fromString("*").name());
    }
}
