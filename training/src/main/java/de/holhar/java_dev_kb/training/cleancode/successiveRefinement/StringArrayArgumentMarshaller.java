package de.holhar.java_dev_kb.training.cleancode.successiveRefinement;

import java.util.Iterator;

public class StringArrayArgumentMarshaller implements ArgumentMarshaller {
    String[] stringArray;

    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {

    }

    public static String[] getValue(ArgumentMarshaller am) {
        return new String[]{""};
    }
}
