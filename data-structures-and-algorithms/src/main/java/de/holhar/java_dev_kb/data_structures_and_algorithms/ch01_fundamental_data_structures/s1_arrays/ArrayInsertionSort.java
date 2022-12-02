package de.holhar.java_dev_kb.data_structures_and_algorithms.ch01_fundamental_data_structures.s1_arrays;

public class ArrayInsertionSort {

  private ArrayInsertionSort() {
  }

  public static void insertionSort(char[] data) {
    int n = data.length;

    // begin with second character
    for (int k = 1; k < n; k++) {
      char cur = data[k];
      int j = k;
      while (j > 0 && data[j - 1] > cur) {
        data[j] = data[j - 1];
        j--;
      }
      data[j] = cur;
    }
  }
}
