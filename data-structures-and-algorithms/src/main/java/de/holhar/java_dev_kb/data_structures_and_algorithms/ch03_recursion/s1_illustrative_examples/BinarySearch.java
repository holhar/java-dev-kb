package de.holhar.java_dev_kb.data_structures_and_algorithms.ch03_recursion.s1_illustrative_examples;

public class BinarySearch {

  /**
   * Returns trie if the target value is found in the indicated portion of the data array.
   * This search only considers the array portion from data[low] to data[high] inclusive.
   */
  public static boolean binarySearch(int[] data, int target, int low, int high) {
    if (low > high) {
      return false;         // interval empty; no match
    } else {
      int mid = (low + high) / 2;

      if (target == data[mid]) {
        return true;
      } else if (target < data[mid]) {
        return binarySearch(data, target, low, mid - 1);
      } else {
        return binarySearch(data, target, mid + 1, high);
      }
    }
  }
}
