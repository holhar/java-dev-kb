package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec02_generics.subsec06_legacy_code;

import java.util.ArrayList;
import java.util.List;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

/**
 * @author hhs@dasburo.com
 */
public class LegacyDragons {

    public static void main(String[] args) {
        List unicorns = new ArrayList();
        unicorns.add(new Unicorn());
        // calling 'printDragons' with a raw type - due to type erasure, Java does not know that this
        // is a problem until runtime. But it knows, that there 'might' be a problem, so we get a compiler warning
        printDragons(unicorns);
        // |-> Why do I get an "unchecked" warning although there is no type information missing?
        /*
         * Because the compiler performs all type checks based on the type erasure when you use a raw type.
         * For more information see: http://angelikalanger.com/GenericsFAQ/FAQSections/TechnicalDetails.html
         */
    }

    public static void printDragons(List<Dragon> dragons) {
        for (Dragon dragon : dragons) { // ClassCastException
            println(dragon);
        }
    }
}

class Dragon {
}

class Unicorn {
}
