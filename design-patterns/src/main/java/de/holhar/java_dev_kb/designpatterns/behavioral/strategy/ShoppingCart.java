package de.holhar.java_dev_kb.designpatterns.behavioral.strategy;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public int calculateTotal() {
        return items.stream()
                .map(Item::getPrice)
                .reduce(0, Integer::sum);
    }

    public void pay(PaymentStrategy paymentStrategy) {
        int amount = calculateTotal();
        paymentStrategy.pay(amount);
    }
}


class Item {

    private final String upcCode;
    private final int price;

    public Item(String upcCode, int price) {
        this.upcCode = upcCode;
        this.price = price;
    }

    public String getUpcCode() {
        return upcCode;
    }

    public int getPrice() {
        return price;
    }
}
