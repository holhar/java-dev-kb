package de.holhar.java_dev_kb.designpatterns.behavioral.visitor;

public interface ItemElement {
    int accept(ShoppingCartVisitor visitor);
}
