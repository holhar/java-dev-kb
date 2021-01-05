package de.holhar.java_dev_kb.katas.codewars.algorithms.arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Pick Peaks (https://www.codewars.com/kata/5279f6fe5ab7f447890006a7)
 * <p>
 * In this kata, you will write a function that returns the positions and the values of the "peaks" (or local maxima)
 * of a numeric array.
 * <p>
 * For example, the array arr = [0, 1, 2, 5, 1, 0] has a peak at position 3 with a value of 5 (since arr[3] equals 5).
 * <p>
 * The output will be returned as a ``Map<String,List>with two key-value pairs:"pos"and"peaks". If there is no peak
 * in the given array, simply return{"pos" => [], "peaks" => []}`.
 * <p>
 * Example: pickPeaks([3, 2, 3, 6, 4, 1, 2, 3, 2, 1, 2, 3]) should return {pos: [3, 7], peaks: [6, 3]} (or equivalent
 * in other languages)
 * <p>
 * All input arrays will be valid integer arrays (although it could still be empty), so you won't need to validate
 * the input.
 * <p>
 * The first and last elements of the array will not be considered as peaks (in the context of a mathematical
 * function, we don't know what is after and before and therefore, we don't know if it is a peak or not).
 * <p>
 * Also, beware of plateaus !!! [1, 2, 2, 2, 1] has a peak while [1, 2, 2, 2, 3] and [1, 2, 2, 2, 2] do not. In case
 * of a plateau-peak, please only return the position and value of the beginning of the plateau. For example: pickPeaks([1, 2, 2, 2, 1]) returns {pos: [1], peaks: [2]} (or equivalent in other languages)
 */
public class PickPeaks {

    private PickPeaks() {}

    private static final Logger LOGGER = LoggerFactory.getLogger(PickPeaks.class);

    private static List<Integer> posList;
    private static List<Integer> peakList;
    private static List<Integer> numList;
    private static Map<String, List<Integer>> peaksMap;

    public static Map<String, List<Integer>> getPeaks(int[] arr) {

        LOGGER.debug("input array: {}", arr);

        int index = 0;
        int plateauStartIndex = 0;
        boolean isPossiblePlateauStart = false;

        Integer prev = null;
        Integer current;
        Integer next = null;
        Integer plateauStartVal = null;

        initDataStructures(arr);
        ListIterator<Integer> it = numList.listIterator();

        while (it.hasNext()) {
            if (it.hasPrevious()) {
                prev = it.previous();
                it.next();
            }

            current = it.next();

            if (it.hasNext()) {
                next = it.next();
                it.previous();
            }

            if (prev != null && prev < current && current > next) {
                updateListsAndMap(index, current);
            }

            if (prev != null && prev < current && current.equals(next)) {
                isPossiblePlateauStart = true;
                plateauStartIndex = index;
                plateauStartVal = current;
            }

            if (isPossiblePlateauStart) {
                if (current.equals(prev) && current > next) {
                    updateListsAndMap(plateauStartIndex, plateauStartVal);
                    isPossiblePlateauStart = false;
                } else if (current < next) {
                    isPossiblePlateauStart = false;
                }
            }

            index++;
        }
        return peaksMap;
    }

    private static void initDataStructures(int[] arr) {
        posList = new ArrayList<>();
        peakList = new ArrayList<>();
        numList = Arrays.stream(arr).boxed().collect(Collectors.toList());
        peaksMap = new HashMap<>();
        peaksMap.put("pos", posList);
        peaksMap.put("peaks", peakList);
    }

    private static void updateListsAndMap(int pos, Integer peak) {
        posList.add(pos);
        peakList.add(peak);
        peaksMap.put("pos", posList);
        peaksMap.put("peaks", peakList);
    }
}
