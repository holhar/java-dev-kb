package de.holhar.java_dev_kb.training.concurrency.ch05_building_blocks.s5_1_synchronized_collections.s5_1_2_iterators_and_concurrent_exceptions;

import de.holhar.java_dev_kb.training.concurrency.ch04_composing_objects.util.Widget;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Iterating a List with an Iterator.
 */
public class IteratingListWithIterator {

  public void doSomething() {
    List<Widget> widgetList = Collections.synchronizedList(new ArrayList<>());

    // ...

    // May throw ConcurrentModificationException
    for (Widget w : widgetList) {
      doSomethingElse(w);
    }
  }

  private void doSomethingElse(Widget w) {
    // ...
  }
}
