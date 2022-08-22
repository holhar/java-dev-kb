package de.holhar.java_dev_kb.designpatterns.behavioral.strategy;

public class ShoppingCartClient {

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        Item item1 = new Item("1234", 10);
        Item item2 = new Item("5678", 40);

        cart.addItem(item1);
        cart.addItem(item2);

        // Pay by PayPal
        cart.pay(new PaypalStrategy("max@power.com", "123456"));

        // Pay by credit card
        cart.pay(new CreditCardStrategy("Max Power", "12345678900987654", "786", "12/15"));
    }
}
