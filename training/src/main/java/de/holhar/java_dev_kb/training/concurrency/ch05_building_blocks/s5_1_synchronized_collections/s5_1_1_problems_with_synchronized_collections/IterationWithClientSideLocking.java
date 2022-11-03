package de.holhar.java_dev_kb.training.concurrency.ch05_building_blocks.s5_1_synchronized_collections.s5_1_1_problems_with_synchronized_collections;

import java.util.Vector;

/**
 * Iteration with client-side locking.
 */
public class IterationWithClientSideLocking {

  public void doSomething(Vector list) {
    synchronized (list) {
      for (int i = 0; i < list.size(); i++) {
        doSomething(list.get(i));
      }
    }
  }

  private void doSomething(Object thing) {
    // ...
  }
}
