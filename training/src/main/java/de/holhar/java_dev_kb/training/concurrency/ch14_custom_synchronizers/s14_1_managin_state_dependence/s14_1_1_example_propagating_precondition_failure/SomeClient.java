package de.holhar.java_dev_kb.training.concurrency.ch14_custom_synchronizers.s14_1_managin_state_dependence.s14_1_1_example_propagating_precondition_failure;

import de.holhar.java_dev_kb.training.concurrency.ch14_custom_synchronizers.utils.BufferEmptyException;

/**
 * Client logic for calling GrumpyBoundedBuffer.
 */
public class SomeClient<V> {

  private static final long SLEEP_GRANULARITY = 1000;

  private final GrumpyBoundedBuffer<V> buffer;

  public SomeClient(GrumpyBoundedBuffer<V> buffer) {
    this.buffer = buffer;
  }

  public void doSomething() throws InterruptedException {
    while (true) {
      try {
        V item = buffer.take();
        // use item
        break;
      } catch (BufferEmptyException e) {
        Thread.sleep(SLEEP_GRANULARITY);
      }
    }
  }
}
