package de.holhar.java_dev_kb.designpatterns.creational.prototype;

public class Ford extends CarPrototype {

    public Ford(String modelName) {
        this.modelName = modelName;
        this.basePrice = 100_000;
    }

    @Override
    public CarPrototype clone() throws CloneNotSupportedException {
        return (Ford)super.clone();
    }
}
