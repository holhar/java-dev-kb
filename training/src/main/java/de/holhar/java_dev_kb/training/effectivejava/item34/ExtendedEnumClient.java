package de.holhar.java_dev_kb.training.effectivejava.item34;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ExtendedEnumClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExtendedEnumClient.class);

    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);

        LOGGER.debug("Test 1:");
        test1(ExtendedOperation.class, x, y);

        LOGGER.debug("Test 2:");
        List<Operation> values1 = Arrays.asList(BasicOperation.values());
        List<Operation> values2 = Arrays.asList(ExtendedOperation.values());
        List<Operation> values3 = new ArrayList<>();
        values3.addAll(values1);
        values3.addAll(values2);
        test2(values3, x, y);
    }

    private static <T extends Enum<T> & Operation> void test1(Class<T> opSet, double x, double y) {
        for (Operation op : opSet.getEnumConstants()) {
            LOGGER.debug("{} {} {} = {}", x, op, y, op.apply(x, y));
        }
    }

    private static void test2(Collection<? extends Operation> opSet, double x, double y) {
        for (Operation op : opSet) {
            LOGGER.debug("{} {} {} = {}", x, op, y, op.apply(x, y));
        }
    }
}
