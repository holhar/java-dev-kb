package de.holhar.java_dev_kb.designpatterns.behavioral.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class ShoppingCartClient {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartClient.class);

    public static void main(String[] args) {
        List<ItemElement> items = Arrays.asList(
                new Book(20, "1234"),
                new Book(100, "5678"),
                new Fruit(10, 2, "Banana"),
                new Fruit(5, 5, "Apple")
        );

        int total = calculatePrice(items);
        logger.info("Total cost = '{}'", total);
    }

    private static int calculatePrice(List<ItemElement> items) {
        ShoppingCartVisitorImpl visitor = new ShoppingCartVisitorImpl();
        return items.stream()
                .map(i -> i.accept(visitor))
                .reduce(0, Integer::sum);
    }
}
