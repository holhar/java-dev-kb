package de.holhar.java_dev_kb.training.cleancode.successiveRefinement;

import java.util.*;

import static de.holhar.java_dev_kb.training.cleancode.successiveRefinement.ArgsException.ErrorCode.INVALID_ARGUMENT_FORMAT;
import static de.holhar.java_dev_kb.training.cleancode.successiveRefinement.ArgsException.ErrorCode.UNEXPECTED_ARGUMENT;

public class Args {

    private Map<Character, ArgumentMarshaller> marshallers;
    private Set<Character> argsFound;
    private ListIterator<String> currentArgument;

    public Args(String schema, String[] argStrings) throws ArgsException {
        marshallers = new HashMap<>();
        argsFound = new HashSet<>();

        parseSchema(schema);
        parseArgumentStrings(Arrays.asList(argStrings));
    }

    private void parseSchema(String schema) throws ArgsException {
        for (String element : schema.split(",")) {
            if (element.length() > 0) {
                parseSchemaElement(element.trim());
            }
        }
    }

    private void parseSchemaElement(String element) throws ArgsException {
        char elementId = element.charAt(0);
        String elementTail = element.substring(1);
        validateSchemaElementId(elementId);

        if (elementTail.length() == 0)
            marshallers.put(elementId, new BooleanArgumentMarshaller());
        else if (elementTail.equals("*"))
            marshallers.put(elementId, new StringArgumentMarshaller());
        else if (elementTail.equals("#"))
            marshallers.put(elementId, new IntegerArgumentMarshaller());
        else if (elementTail.equals("##"))
            marshallers.put(elementId, new DoubleArgumentMarshaller());
        else if (elementTail.equals("[*]"))
            marshallers.put(elementId, new StringArrayArgumentMarshaller());
        else
            throw new ArgsException(INVALID_ARGUMENT_FORMAT, elementId, elementTail);
    }

    private void validateSchemaElementId(char elementId) throws ArgsException {
        if (!Character.isLetter(elementId)) {
            throw new ArgsException(INVALID_ARGUMENT_FORMAT, elementId, null);
        }
    }

    private void parseArgumentStrings(List<String> argsList) throws ArgsException {
        for (currentArgument = argsList.listIterator(); currentArgument.hasNext(); ) {
            String argString = currentArgument.next();
            if (argString.startsWith("-")) {
                parseArgumentCharacters(argString.substring(1));
            } else {
                currentArgument.previous(); // TODO: why the call to previous()?
                break;
            }
        }
    }

    private void parseArgumentCharacters(String argChars) throws ArgsException {
        for (int i = 0; i < argChars.length(); i++) {
            parseArgumentCharacter(argChars.charAt(i));
        }
    }

    private void parseArgumentCharacter(char argChar) throws ArgsException {
        ArgumentMarshaller m = marshallers.get(argChar);
        if (m == null) {
            throw new ArgsException(UNEXPECTED_ARGUMENT, argChar, null);
        } else {
            argsFound.add(argChar);
            try {
                m.set(currentArgument);
            } catch (ArgsException e) {
                e.setErrorArgumentId(argChar);
                throw e;
            }
        }
    }

    public boolean has(char arg) {
        return argsFound.contains(arg);
    }

    public int nextArgument() {
        return currentArgument.nextIndex();
    }

    public boolean getBoolean(char arg) {
        return BooleanArgumentMarshaller.getValue(marshallers.get(arg));
    }

    public String getString(char arg) {
        return StringArgumentMarshaller.getValue(marshallers.get(arg));
    }

    public int getInt(char arg) {
        return IntegerArgumentMarshaller.getValue(marshallers.get(arg));
    }

    public double getDouble(char arg) {
        return DoubleArgumentMarshaller.getValue(marshallers.get(arg));
    }

    public String[] getStringArray(char arg) {
        return StringArrayArgumentMarshaller.getValue(marshallers.get(arg));
    }
}
