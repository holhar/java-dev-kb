package de.holhar.java_dev_kb.katas.codewars.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Split by Value (https://www.codewars.com/kata/5a433c7a8f27f23bb00000dc)
 *
 * For an integer k rearrange all the elements of the given array in such way, that:
 *
 * all elements that are less than k are placed before elements that are not less than k;
 * all elements that are less than k remain in the same order with respect to each other;
 * all elements that are not less than k remain in the same order with respect to each other.
 *
 * For k = 6 and elements = [6, 4, 10, 10, 6], the output should be splitByValue(k, elements) = [4, 6, 10, 10, 6].
 *
 * For k = 5 and elements = [1, 3, 5, 7, 6, 4, 2], the output should be splitByValue(k, elements) = [1, 3, 4, 2, 5, 7, 6].
 *
 * S: codefights.com
 */
public class SplitByValue {
    int[] splitByValue(int k, int[] elements) {
        List<Integer> before = new ArrayList<>();
        List<Integer> after = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        for (Integer e : elements) {
            if (e < k)
                before.add(e);
            else
                after.add(e);
        }

        result.addAll(before);
        result.addAll(after);

        int[] resultArr = new int[result.size()];

        int i = 0;
        for (Integer e : result) {
            resultArr[i] = e;
            i++;
        }

        return resultArr;
    }
}
