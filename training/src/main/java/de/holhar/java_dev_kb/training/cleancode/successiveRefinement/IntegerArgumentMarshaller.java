package de.holhar.java_dev_kb.training.cleancode.successiveRefinement;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static de.holhar.java_dev_kb.training.cleancode.successiveRefinement.ArgsException.ErrorCode.INVALID_INTEGER;
import static de.holhar.java_dev_kb.training.cleancode.successiveRefinement.ArgsException.ErrorCode.MISSING_INTEGER;

public class IntegerArgumentMarshaller implements ArgumentMarshaller {
    int intValue = 0;

    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {
        String parameter = null;
        try {
            parameter = currentArgument.next();
            intValue = Integer.parseInt(parameter);
        } catch (NoSuchElementException e) {
            throw new ArgsException(MISSING_INTEGER);
        } catch (NumberFormatException e) {
            throw new ArgsException(INVALID_INTEGER, parameter);
        }
    }

    public static int getValue(ArgumentMarshaller am) {
        if (am != null && am instanceof IntegerArgumentMarshaller) {
            return ((IntegerArgumentMarshaller) am).intValue;
        } else {
            return 0;
        }
    }
}
