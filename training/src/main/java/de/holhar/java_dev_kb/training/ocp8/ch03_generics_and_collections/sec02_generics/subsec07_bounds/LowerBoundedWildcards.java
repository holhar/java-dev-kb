package de.holhar.java_dev_kb.training.ocp8.ch03_generics_and_collections.sec02_generics.subsec07_bounds;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LowerBoundedWildcards {

    public static void main(String[] args) {
        List<? super IOException> exceptions = new ArrayList<Exception>();
        // exceptions.add(new Exception()); // DOES NOT COMPILE because we could have a List<IOException> and an Exception object wouldn't fit in there.
        exceptions.add(new IOException());
        exceptions.add(new FileNotFoundException());
    }
}
