package de.holhar.java_dev_kb.training.ocp8.ch05_dates_string_localization.subsec02_string_class_review;

import static de.holhar.java_dev_kb.training.ocp8.utils.OcpPrepUtils.println;

public class StringClassReview {

    public static void main(String[] args) {

        // String pool
        String s1 = "bunny"; // goes into String pool
        String s2 = "bunny"; // gets referenced to the String already in String pool
        String s3 = new String("bunny");
        println(s1 == s2);        // true
        println(s1 == s3);        // false
        println(s1.equals(s3));   // true

        // String concatenation
        String s4 = "1" + 2 + 3;
        println(s4);  // 123
        String s5 = 1 + 2 + "3";
        println(s5);  // 33

        // Common methods
        String s = "abcde ";
        println(s.trim().length());   // 5
        println(s.charAt(4));         // e
        println(s.indexOf('e'));      // 4
        println(s.indexOf("de"));     // 3
        println(s.substring(2, 4).toUpperCase());         // CD
        println(s.replace('a', '1'));     // 1bcde
        println(s.contains("DE"));    // false
        println(s.startsWith("a"));   // true

        // Using StringBuilder
        StringBuilder b = new StringBuilder();
        b.append(12345).append('-');
        println(b.length());      // 6
        println(b.indexOf("-"));  // 5
        println(b.charAt(2));     // 3

        StringBuilder b2 = b.reverse();
        println(b2.toString());   // -54321
        println(b == b2);         // true

        StringBuilder b3 = new StringBuilder("abcde");
        b3.insert(1, '-').delete(3, 4);
        println(b3);                  // a-bde
        println(b3.substring(2, 4));  // bd
    }
}
