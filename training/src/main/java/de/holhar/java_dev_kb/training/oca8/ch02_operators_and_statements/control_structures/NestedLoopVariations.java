package de.holhar.java_dev_kb.training.oca8.ch02_operators_and_statements.control_structures;

import static de.holhar.java_dev_kb.training.oca8.util.Oca8Utils.print;

public class NestedLoopVariations {

    public static void main(String[] args) {
        String version = args[0];
        print("Version is '" + version + "'");
        print(doLoop(version));
    }

    private static int doLoop(String version) {
        int ctr = 10;
        char[] arrC1 = new char[]{'P', 'a', 'u', 'l'};
        char[] arrC2 = new char[]{'H', 'a', 'r', 'r', 'y'};

        if (version.equals("1")) {
            for (char c1 : arrC1) {
                for (char c2 : arrC2) {
                    if (c2 == 'a') break;
                    ++ctr;
                }
            }
        } else if (version.equals("2")) {
            for (char c1 : arrC1)
                for (char c2 : arrC2) {
                    if (c2 == 'a') break;
                    ++ctr;
                }
        } else if (version.equals("3")) {
            for (char c1 : arrC1)
                for (char c2 : arrC2)
                    if (c2 == 'a') break;
            ++ctr;
        } else if (version.equals("4")) {
            for (char c1 : arrC1) {
                for (char c2 : arrC2) {
                    if (c2 == 'a') continue;
                    ++ctr;
                }
            }
        }
        return ctr;
    }
}
