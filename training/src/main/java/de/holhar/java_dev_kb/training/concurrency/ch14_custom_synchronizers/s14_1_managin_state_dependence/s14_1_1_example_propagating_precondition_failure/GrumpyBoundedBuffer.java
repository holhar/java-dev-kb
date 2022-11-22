package de.holhar.java_dev_kb.training.concurrency.ch14_custom_synchronizers.s14_1_managin_state_dependence.s14_1_1_example_propagating_precondition_failure;

import de.holhar.java_dev_kb.training.concurrency.ch14_custom_synchronizers.s14_1_managin_state_dependence.BaseBoundedBuffer;
import de.holhar.java_dev_kb.training.concurrency.ch14_custom_synchronizers.utils.BufferEmptyException;
import de.holhar.java_dev_kb.training.concurrency.ch14_custom_synchronizers.utils.BufferFullException;
import de.holhar.java_dev_kb.training.concurrency.utils.ThreadSafe;

/**
 * Bounded buffer that balks when preconditions are not met.
 * NOT IDEAL.
 */
@ThreadSafe
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

  protected GrumpyBoundedBuffer(int capacity) {
    super(capacity);
  }

  public synchronized void put(V v) throws BufferFullException {
    if (isFull()) {
      throw new BufferFullException();
    }
    doPut(v);
  }

  public synchronized V take() throws BufferEmptyException {
    if (isEmpty()) {
      throw new BufferEmptyException();
    }
    return doTake();
  }
}
