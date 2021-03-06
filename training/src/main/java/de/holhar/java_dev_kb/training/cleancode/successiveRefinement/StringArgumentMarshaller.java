package de.holhar.java_dev_kb.training.cleancode.successiveRefinement;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static de.holhar.java_dev_kb.training.cleancode.successiveRefinement.ArgsException.ErrorCode.MISSING_STRING;

public class StringArgumentMarshaller implements ArgumentMarshaller {
    private String stringValue = "";

    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {
        try {
            stringValue = currentArgument.next();
        } catch (NoSuchElementException e) {
            throw new ArgsException(MISSING_STRING);
        }
    }

    public static String getValue(ArgumentMarshaller am) {
        if (am != null && am instanceof StringArgumentMarshaller) {
            return ((StringArgumentMarshaller) am).stringValue;
        } else {
            return "";
        }
    }
}
