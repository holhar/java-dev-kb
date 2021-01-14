package de.holhar.java_dev_kb.training.oca8.ch01_java_building_blocks;

public class PrimitivePlayground {

    public static void main(String[] args) {
        // long max = 3123456789; => too large for an integer - DOES NOT COMPILE
        long max = 3123456789L;

        // Valid usages of number systems
        System.out.println(56);    // decimal
        System.out.println(0b11);    // binary
        System.out.println(017);    // octal
        System.out.println(0x1F);    // hexadecimal

        // Invalid usages of underscores in numbers
        //double notAtStart = _1000:00;
        //double notAtEnd = 1000.00_;
        //double notByDecimal = 1000_.00;

        // This one compiles:
        double annoyingButLegal = 1_00_0.0_0;
    }
}
