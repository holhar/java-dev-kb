package de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec01_oca_concepts_review.subsec01_access_modifiers.cat.species;

import de.holhar.java_dev_kb.training.ocp8.ch01_advanced_class_design.sec01_oca_concepts_review.subsec01_access_modifiers.cat.BigCat;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class Lynx extends BigCat {

    public static void main(String[] args) {
        BigCat cat = new BigCat();
        println(cat.name);        // allowed, it's public
        //println(cat.hasFur); 	// NOT allowed, it's accessed via direct call of parent reference
        // => via Lynx reference or methods using inheritance it would work:
        println(cat.hasFur());

        Lynx lynx = new Lynx();
        println(lynx.hasFur);    // Lynx is now seen as a subclass and hasFur is accessible
        //println(cat.hasPaws);	// NOT allowed, as it's package-private
        //println(cat.id);		// NOT allowed, as it's private
    }
}
