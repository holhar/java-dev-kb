package de.holhar.java_dev_kb.designpatterns.behavioral.chain_of_responsibility;

public final class Currency {

    private final int amount;

    public Currency(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
