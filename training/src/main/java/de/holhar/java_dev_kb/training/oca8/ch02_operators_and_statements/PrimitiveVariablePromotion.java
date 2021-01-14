package de.holhar.java_dev_kb.training.oca8.ch02_operators_and_statements;

import static de.holhar.java_dev_kb.training.oca8.util.Oca8Utils.print;

public class PrimitiveVariablePromotion {

    public static void main(String[] args) {

        char charVal = 'c';
        // This will work
        charVal = 1000;
        // Won't compile: error: incompatible types: possible lossy conversion from int/long/float/double to char
        // charVal = 1000000;
        // charVal = 10000000000L;
        // charVal = 4.0f;
        // charVal = 5.0;

        byte byteVal = 100;
        // Won't compile: error: incompatible types: possible lossy conversion from int/long/float/double to byte
        // byteVal = 157;
        // byteVal = 4L;
        // byteVal = 4.0f;
        // byteVal = 5.0;

        short shortVal = 1000;
        // Won't compile: error: incompatible types: possible lossy conversion from int/long/float/double to short
        // shortVal = 56000;
        // shortVal = 4L;
        // shortVal = 4.0f;
        // shortVal = 5.0;

        int intVal = 100000;
        // Won't compile - 'integer number is too large'
        // intVal = 1000000000000;
        // Won't compile: error: incompatible types: possible lossy conversion from long/float/double to int
        // intVal = 4L;
        // intVal = 4.0f;
        // intVal = 5.0;

        long longVal = 10000000000L;
        // This will work as long as the int value is not too large for an int
        longVal = 56;
        // Won't compile: error: incompatible types: possible lossy conversion from float/double to long
        // longVal = 4.0f;
        // longVal = 5.0;

        float floatVal = 45.5f;
        // A float can be assigned an int or a long value - the literals will be promoted to float
        floatVal = 45;
        floatVal = 45L;
        // Won't compile: error: incompatible types: possible lossy conversion from double to float
        // floatVal = 5.0;

        double doubleVal = 0.00000001;
        // A double can be assigned an int, a long, or a double value - the literals will be promoted to double
        doubleVal = 45;
        doubleVal = 45L;
        doubleVal = 4.0f;

        /*
         * Numeric Promotion Rules
         *
         * 1. If two values have different data types, Java will automatically promote one of the value to the
         *    larger of the two data types.
         * 2. If one of the values is integral and the other is floating-point, Java will automatically promote
         *    the integral value to the floating-point value's data type.
         * 3. Smaller data types, namely byte, short, and char, are first promoted to int any time they're used
         *    with a Java binary arithmetic operator, even if neither of the operands is int.
         * 4. After all promotion has occurred and the operands have the same data type, the resulting value will
         *    have the same data type as its promoted operands.
         *
         * Examples:
         */

        // The integer x will be promoted to long (see rule 1).
        int x = 1;
        long y = 30;
        long result = x * y;

        // Both short values a and b will be promoted to int before the operation will be processed (see rule 3).
        short a = 20;
        short b = 5;
        int result2 = a / b;

        // First, i will be promoted to int, then i will be promoted to float (i * j), then the result of the will
        // be promoted to double, and finally the result of the operation will be promoted to double as well (see
        // all of the rules).
        short i = 14;
        float j = 13;
        double k = 30;
        double result3 = i * j / k;

        print("No exceptions - Yay!");
    }
}
