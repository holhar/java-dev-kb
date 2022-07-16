package de.holhar.java_dev_kb.designpatterns.creational.prototype;

public class Nano extends CarPrototype {

    public Nano(String modelName) {
        this.modelName = modelName;
        this.basePrice = 100_00;
    }

    @Override
    public CarPrototype clone() throws CloneNotSupportedException {
        return (Nano)super.clone();
    }
}
