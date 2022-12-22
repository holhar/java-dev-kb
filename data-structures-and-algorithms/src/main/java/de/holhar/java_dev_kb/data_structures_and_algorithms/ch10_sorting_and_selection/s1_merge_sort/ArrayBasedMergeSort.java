package de.holhar.java_dev_kb.data_structures_and_algorithms.ch10_sorting_and_selection.s1_merge_sort;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayBasedMergeSort {

  /**
   * Merge-sort contents of array S.
   */
  public static <K> void mergeSort(K[] s, Comparator<K> comparator) {
    int n = s.length;

    if (n < 2)
      return;

    // divide
    int mid = n/2;
    K[] s1 = Arrays.copyOfRange(s, 0, mid);   // copy first half
    K[] s2 = Arrays.copyOfRange(s, mid, n);         // copy second half

    // conquer (with recursion)
    mergeSort(s1, comparator);
    mergeSort(s2, comparator);

    // merge sorted halves back into original
    merge(s1, s2, s, comparator);
  }

  /**
   * Merge contents of arrays s1 and s2 into properly sized array s.
   */
  private static <K> void merge(K[] s1, K[] s2, K[] s, Comparator<K> comparator) {
    int i = 0;
    int j = 0;

    while (i + j < s.length) {
      if (j == s2.length || (i < s1.length && comparator.compare(s1[i], s2[j]) < 0)) {
        s[i+j] = s1[i++];       // copy ith element of s1 and increment i
      } else {
        s[i+j] = s2[j++];       // copy jth element of s2 and increment j
      }
    }
  }
}
