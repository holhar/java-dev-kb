package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec01_oca_concepts_review.subsec02_overloading_and_overriding;

public class BobcatKitten extends Bobcat {

    // Overrides the findDen() method from the parent class
    public void findDen() {
    }

    // Overloads the findDen() method, as the name is the same, but the params differ
    public void findDen(boolean b) {
    }

    // This is a new method, it has a new name; also the return type is not covariant
    // and the exception declaration is new (this wouldn't be valid for overriding)
    public int findden() throws Exception {
        return 0;
    }
}
