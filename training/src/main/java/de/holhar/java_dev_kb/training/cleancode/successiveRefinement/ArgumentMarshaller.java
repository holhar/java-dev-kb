package de.holhar.java_dev_kb.training.cleancode.successiveRefinement;

import java.util.Iterator;

public interface ArgumentMarshaller {
    void set(Iterator<String> currentArgument) throws ArgsException;
}
