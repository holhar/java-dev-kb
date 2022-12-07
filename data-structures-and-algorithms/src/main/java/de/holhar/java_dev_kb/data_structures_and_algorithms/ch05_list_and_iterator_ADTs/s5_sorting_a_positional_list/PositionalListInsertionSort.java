package de.holhar.java_dev_kb.data_structures_and_algorithms.ch05_list_and_iterator_ADTs.s5_sorting_a_positional_list;

import de.holhar.java_dev_kb.data_structures_and_algorithms.ch05_list_and_iterator_ADTs.s3_positional_lists.Position;
import de.holhar.java_dev_kb.data_structures_and_algorithms.ch05_list_and_iterator_ADTs.s3_positional_lists.PositionalList;

public class PositionalListInsertionSort {

  /**
   * Insertion-sort of a positional list of integers into non-decreasing order
   */
  public static void insertionSort(PositionalList<Integer> list) {
    Position<Integer> marker = list.first(); // last position known to be sorted

    while (marker != list.last()) {
      Position<Integer> pivot = list.after(marker);
      int value = pivot.getElement();     // number to be placed

      if (value > marker.getElement()) {  // pivot is already sorted
        marker = pivot;
      } else {                            // must relocate pivot
        Position<Integer> walk = marker;  // find leftmost item greater than value

        while (walk != list.first() && list.before(walk).getElement() > value) {
          walk = list.before(walk);
        }

        list.remove(pivot);               // remove pivot entry and
        list.addBefore(walk, value);      // reinsert value in front of walk
      }
    }
  }
}
