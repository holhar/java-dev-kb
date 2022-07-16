package de.holhar.java_dev_kb.designpatterns.creational.prototype;

import java.util.Objects;
import java.util.Random;

public abstract class CarPrototype implements Cloneable {

    protected String modelName;
    protected int basePrice;
    protected int onRoadPrice;

    private static final Random r = new Random();

    /*
     * Will set onRoadPrice by subtracting an integer value in the range 0 to 100_000 from basePrice
     */
    public void calculateOnRoadPrice() {
        this.onRoadPrice = this.basePrice - r.nextInt(100_000);
    }

    public String getModelName() {
        return modelName;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getOnRoadPrice() {
        return onRoadPrice;
    }

    public void setOnRoadPrice(int onRoadPrice) {
        this.onRoadPrice = onRoadPrice;
    }

    @Override
    public CarPrototype clone() throws CloneNotSupportedException {
        return (CarPrototype)super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarPrototype)) return false;
        CarPrototype that = (CarPrototype) o;
        return basePrice == that.basePrice && onRoadPrice == that.onRoadPrice && modelName.equals(that.modelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelName, basePrice);
    }

    @Override
    public String toString() {
        return "CarPrototype{" +
                "modelName='" + modelName + '\'' +
                ", basePrice=" + basePrice +
                ", onRoadPrice=" + onRoadPrice +
                '}';
    }
}
