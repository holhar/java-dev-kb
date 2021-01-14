package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec01_oca_concepts_review.subsec04_static_and_final;

/*
 * To which lines of the following code could you independently add static and/or final without
 * introducing a compiler error?
 */
abstract class Cat {
    // Can be both, static and final
    String name = "The Cat";

    // Can not be final, as it's been overriden by Lion and can only be static if also the child
    // class is static
    void clean() {
    }
}

class Lion extends Cat {
    // Can final final, and can only be static if the method in the parent class is also static
    void clean() {
    }
}
