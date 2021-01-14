package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec01_oca_concepts_review.subsec03_abstract_classes;

abstract class Cat {
    // Three possibilities to make this class including the class Lion compile
    // 1. leave the class blank
    // 2. provide the abstract version of 'clean'
    abstract void clean();

    // 3. provide concrete implementation, that gets overridden
    public void clean2() {
        return;
    }
}
