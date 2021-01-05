package de.holhar.java_dev_kb.katas.codewars.algorithms.strings;

/**
 * Range Extraction (https://www.codewars.com/kata/51ba717bb08c1cd60f00002f)
 * <p>
 * A format for expressing an ordered list of integers is to use a comma separated list of either
 * <p>
 * individual integers
 * or a range of integers denoted by the starting integer separated from the end integer in the range by a dash,
 * '-'. The range includes all integers in the interval including both endpoints. It is not considered a range
 * unless it spans at least 3 numbers. For example "12,13,15-17"
 * <p>
 * Complete the solution so that it takes a list of integers in increasing order and returns a correctly formatted
 * string in the range format.
 * <p>
 * Example:
 * <p>
 * Solution.rangeExtraction(new int[] {-6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20})
 * # returns "-6,-3-1,3-5,7-11,14,15,17-20"
 * <p>
 * Courtesy of rosettacode.org
 */
public class RangeExtraction {

    private RangeExtraction() {}

    public static String rangeExtraction(int[] arr) {

        StringBuilder result = new StringBuilder(2000);

        int tempIndex;
        int end;
        int steps;
        int limiter = arr.length - 1;

        for (int i = 0; i < arr.length; i++) {
            tempIndex = i;
            end = i + 1;
            steps = 0;

            while (end <= limiter) {
                if (arr[tempIndex] + 1 == arr[end]) {
                    tempIndex++;
                    end++;
                    steps++;
                } else {
                    break;
                }
            }

            if (steps >= 2) {
                result.append(arr[i])
                        .append("-")
                        .append(arr[end - 1]);
                i = end - 1;
            } else {
                result.append(arr[i]);
            }

            if (i < limiter) {
                result.append(",");
            }
        }

        return result.toString();
    }
}
