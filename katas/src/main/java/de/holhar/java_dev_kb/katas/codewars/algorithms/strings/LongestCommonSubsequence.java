package de.holhar.java_dev_kb.katas.codewars.algorithms.strings;

/**
 * Longest Common Subsequence (https://www.codewars.com/kata/52756e5ad454534f220001ef)
 * <p>
 * Write a function called LCS that accepts two sequences and returns the longest subsequence common to the passed in
 * sequences.
 * Subsequence
 * <p>
 * A subsequence is different from a substring. The terms of a subsequence need not be consecutive terms of the
 * original sequence.
 * <p>
 * Example subsequence
 * <p>
 * Subsequences of "abc" = "a", "b", "c", "ab", "ac", "bc" and "abc".
 * LCS examples
 * <p>
 * Solution.lcs("abcdef", "abc") => returns "abc"
 * Solution.lcs("abcdef", "acf") => returns "acf"
 * Solution.lcs("132535365", "123456789") => returns "12356"
 * <p>
 * Notes
 * <p>
 * Both arguments will be strings
 * Return value must be a string
 * Return an empty string if there exists no common subsequence
 * Both arguments will have one or more characters (in JavaScript)
 * All tests will only have a single longest common subsequence. Don't worry about cases such as LCS( "1234",
 * "3412" ), which would have two possible longest common subsequences: "12" and "34".
 * <p>
 * Note that the Haskell variant will use randomized testing, but any longest common subsequence will be valid.
 * <p>
 * Note that the OCaml variant is using generic lists instead of strings, and will also have randomized tests (any
 * longest common subsequence will be valid).
 */
public class LongestCommonSubsequence {

    private LongestCommonSubsequence() {}

    public static String lcs(String x, String y) {
        int xLen = x.length();
        int yLen = y.length();

        if (xLen == 0 || yLen == 0) {
            return "";
        }

        if (x.charAt(xLen - 1) == y.charAt(yLen - 1)) {
            return lcs(x.substring(0, xLen - 1), y.substring(0, yLen - 1))
                    + x.charAt(xLen - 1);
        } else {
            String a = lcs(x, y.substring(0, yLen - 1));
            String b = lcs(x.substring(0, xLen - 1), y);
            return (a.length() > b.length()) ? a : b;
        }
    }
}
