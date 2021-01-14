package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec01_oca_concepts_review.subsec01_access_modifiers.cat;

public class BigCat {
    public String name = "cat";
    protected boolean hasFur = true;
    boolean hasPaws = true;
    private int id;

    public boolean hasFur() {
        return hasFur;
    }
}
