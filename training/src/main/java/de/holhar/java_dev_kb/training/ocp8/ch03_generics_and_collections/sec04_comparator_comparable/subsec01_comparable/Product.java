package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec04_comparator_comparable.subsec01_comparable;

public class Product implements Comparable<Product> {

    int id;
    String name;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product)) {
            return false;
        }
        Product other = (Product) o;
        return this.id == other.id;
    }

    @Override
    public int compareTo(Product p) {
        // THAT'S A NO NO!!!!
        return this.name.compareTo(p.name);
    }
}
