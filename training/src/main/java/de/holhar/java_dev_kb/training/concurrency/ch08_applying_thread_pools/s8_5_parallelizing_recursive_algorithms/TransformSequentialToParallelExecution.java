package de.holhar.java_dev_kb.training.concurrency.ch08_applying_thread_pools.s8_5_parallelizing_recursive_algorithms;

import java.util.List;
import java.util.concurrent.Executor;
import javax.swing.text.html.parser.Element;

/**
 * Transforming sequential execution into parallel execution.
 */
public class TransformSequentialToParallelExecution {

  void processSequentially(List<Element> elements) {
    for (Element e : elements) {
      process(e);
    }
  }

  void processInParallel(Executor exec, List<Element> elements) {
    for (final Element e : elements) {
      exec.execute(() -> process(e));
    }
  }

  private void process(Element e) {
    // ...
  }
}
