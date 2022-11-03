package de.holhar.java_dev_kb.training.concurrency.ch05_building_blocks.s5_1_synchronized_collections.s5_1_1_problems_with_synchronized_collections;

import java.util.Vector;

/**
 * Iteration that may throw ArrayIndexOutOfBoundsException.
 */
public class IterationThatMayProduceException {

  public void doSomething(Vector list) {
    for (int i = 0; i < list.size(); i++) {
      doSomething(list.get(i));
    }
  }

  private void doSomething(Object thing) {
    // ...
  }
}
