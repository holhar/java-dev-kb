package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec01_oca_concepts_review.subsec01_access_modifiers.cat;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class CatAdmirer {

    public static void main(String[] args) {
        BigCat cat = new BigCat();
        println(cat.name);        // allowed, it's public
        println(cat.hasFur);    // allowed, it's protected
        println(cat.hasPaws);    // allowed, as it's package-private
        //println(cat.id);			// NOT allowed, as it's private
    }
}
