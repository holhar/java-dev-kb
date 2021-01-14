package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec02_generics.subsec06_legacy_code;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hhs@dasburo.com
 */
public class LegacyAutoboxing {

    public static void main(String[] args) {
        List numbers = new ArrayList();
        numbers.add(5);

        // int result = numbers.get(0); // DOES NOT COMPILE -
    }
}
