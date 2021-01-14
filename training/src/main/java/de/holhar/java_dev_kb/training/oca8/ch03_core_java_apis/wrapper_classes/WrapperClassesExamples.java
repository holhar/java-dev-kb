package de.holhar.java_dev_kb.training.oca8.ch03_core_java_apis.wrapper_classes;

import static de.holhar.java_dev_kb.training.oca8.util.Oca8Utils.print;

public class WrapperClassesExamples {

    public static void main(String[] args) {

        print("Wrapper classes");
        print("===============");

        // Init wrapper classes via constructor
        Boolean boolVal = new Boolean(true);
        Byte byteVal = new Byte((byte) 1);
        Short shortVal = new Short((short) 1);
        Integer intVal = new Integer(1);
        Long longVal = new Long(1);
        Float floatVal = new Float(1.0);
        Double doubleVal = new Double(1.0);
        Character charVal = new Character('c');

        // Converting between primitives and wrapper classes
        boolean boolPrimitive = Boolean.parseBoolean("true");
        Boolean boolWrapper = Boolean.valueOf("TRUE");
        boolean directBoolWrapperToPrimitive = boolWrapper.booleanValue();

        short shortPrimitive = Short.parseShort("45");
        Short shortWrapper = Short.valueOf("45");
        short directShortWrapperToPrimitive = shortWrapper.shortValue();

        byte bytePrimitive = Byte.parseByte("55");
        Byte byteWrapper = Byte.valueOf("55");
        byte directByteWrapperToPrimitive = byteWrapper.byteValue();

        int intPrimitive = Integer.parseInt("123");
        Integer intWrapper = Integer.valueOf("123");
        int directIntWrapperToPrimitive = intWrapper.intValue(); // Not necessary to memorize

        // String parameter values must be valid for the corresponding types
        // int badIntPrimitive = Integer.parseInt("a");		// throws NumberFormatException
        // Integer badIntWrapper = Integer.valueOf("123.45");	// throws NumberFormatException

        long longPrimitive = Long.parseLong("290384729830");
        Long longWrapper = Long.valueOf("983745988973");
        long directLongWrapperToPrimitive = longWrapper.longValue();

        float floatPrimitive = Float.parseFloat("598.54");
        Float floatWrapper = Float.valueOf("98459");
        float directFloatWrapperToPrimitive = floatWrapper.floatValue();

        double doublePrimitive = Double.parseDouble("0.485739485738947");
        Double doubleWrapper = Double.valueOf("4.49583024579309");
        double directDoubleWrapperToPrimitive = doubleWrapper.doubleValue();

        // Character does not have corresponding 'parseCharacter()' and 'valueOf()' methods
        // Since a String is made up of characters, you can just call 'charAt()' normally

        // NOTE: All ***Value() are not relevant to the exam, because since Java 5 we have
        // autoboxing
    }
}
