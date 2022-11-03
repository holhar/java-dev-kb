package de.holhar.java_dev_kb.training.concurrency.ch05_building_blocks.s5_1_synchronized_collections.s5_1_1_problems_with_synchronized_collections;

import java.util.Vector;

/**
 * Compound actions on a Vector that may produce confusing results.
 */
public class CompoundActions {

  public static Object getLast(Vector list) {
    int lastIndex = list.size() - 1;
    return list.get(lastIndex);
  }

  public static void deleteLast(Vector list) {
    int lastIndex = list.size() - 1;
    list.remove(lastIndex);
  }
}
