package de.holhar.java_dev_kb.training.pcps.ch01_container.s0101_di;

public class Store {

    private Item item;

    /**
     * Setting up an object dependency the traditional way.
     * The object has to know about the concrete type of the dependency.
     */
    public Store() {
        item = new ItemImpl();
    }

    /**
     * With DI the concrete type, i.e. the dependency, gets injected into the object.
     * This task is done by an outer construct, establishing the objects and dependencies.
     * The outer construct is provided by the Spring framework in the form of the ApplicationContext.
     */
    public Store(Item item) {
        this.item = item;
    }
}
