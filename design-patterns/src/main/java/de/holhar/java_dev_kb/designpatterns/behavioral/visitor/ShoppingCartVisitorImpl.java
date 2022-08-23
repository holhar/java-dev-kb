package de.holhar.java_dev_kb.designpatterns.behavioral.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShoppingCartVisitorImpl implements ShoppingCartVisitor {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartVisitorImpl.class);

    @Override
    public int visit(Book book) {
        int cost = 0;
        // Apply 5$ discount if book price is greater than 50
        if (book.getPrice() > 50) {
            cost = book.getPrice() - 5;
        } else {
            cost = book.getPrice();
        }
        logger.info("Book ISBN '{}', cost='{}'", book.getIsbnNumber(), cost);
        return cost;
    }

    @Override
    public int visit(Fruit fruit) {
        int cost = fruit.getPricePerKg() * fruit.getWeight();
        logger.info("'{}', cost='{}'", fruit.getName(), cost);
        return cost;
    }
}
