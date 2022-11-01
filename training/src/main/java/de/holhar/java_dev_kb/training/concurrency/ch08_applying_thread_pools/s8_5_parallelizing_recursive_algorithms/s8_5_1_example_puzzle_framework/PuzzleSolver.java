package de.holhar.java_dev_kb.training.concurrency.ch08_applying_thread_pools.s8_5_parallelizing_recursive_algorithms.s8_5_1_example_puzzle_framework;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Solver that recognizes when no solution exists.
 */
public class PuzzleSolver<P, M> extends ConcurrentPuzzleSolver<P, M> {

  private final AtomicInteger taskCount = new AtomicInteger(0);

  public PuzzleSolver(Puzzle<P, M> puzzle) {
    super(puzzle);
  }

  @Override
  protected Runnable newTask(P p, M m, Node<P, M> n) {
    return new CountingSolverTask(p, m, n);
  }

  class CountingSolverTask extends SolverTask {

    public CountingSolverTask(P pos, M move, Node<P, M> prev) {
      super(pos, move, prev);
      taskCount.incrementAndGet();
    }

    public void run() {
      try {
        super.run();
      } finally {
        if (taskCount.decrementAndGet() == 0) {
          solution.setValue(null);
        }
      }
    }
  }
}
