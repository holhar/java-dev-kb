package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec03_virtual_method_invocation;

public class AnimalFeeder {

    /*
     * Virtual methods are actually regular non-static methods. Java looks for an overridden method rather than
     * necessarily using the one in the class that the compiler says we have.
     */
    public static void main(String[] args) {
        Animal cow = new Cow();
        cow.feed();
        Animal bird = new Bird();
        bird.feed();
        Lion lion = new Lion();
        lion.feed();
    }
}
