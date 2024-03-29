package de.holhar.java_dev_kb.training.concurrency.ch08_applying_thread_pools.s8_5_parallelizing_recursive_algorithms.s8_5_1_example_puzzle_framework;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Concurrent version of puzzle solver.
 */
public class ConcurrentPuzzleSolver<P, M> {
  private final Puzzle<P, M> puzzle;
  private final ExecutorService exec;
  private final ConcurrentMap<P, Boolean> seen;
  final ValueLatch<Node<P, M>> solution = new ValueLatch<>();

  public ConcurrentPuzzleSolver(Puzzle<P, M> puzzle) {
    this.puzzle = puzzle;
    this.exec = Executors.newCachedThreadPool();
    this.seen = new ConcurrentHashMap<>();
  }

  public List<M> solve() throws InterruptedException {
    try {
      P p = puzzle.initialPosition();
      exec.execute(newTask(p, null, null));

      // block until solution found
      Node<P, M> solnNode = solution.getValue();
      return (solnNode == null) ? null : solnNode.asMoveList();
    } finally {
      exec.shutdown();
    }
  }

  protected Runnable newTask(P p, M m, Node<P, M> n) {
    return new SolverTask(p, m, n);
  }

  class SolverTask extends Node<P, M> implements Runnable {

    public SolverTask(P pos, M move, Node<P, M> prev) {
      super(pos, move, prev);
    }

    @Override
    public void run() {
      if (solution.isSet() || seen.putIfAbsent(pos, true) != true) {
        return; // already solved or seen this position
      }

      if (puzzle.isGoal(pos)) {
        solution.setValue(this);
      } else {
        for (M m : puzzle.legalMoves(pos)) {
          exec.execute(newTask(puzzle.move(pos, m), m, this));
        }
      }
    }
  }
}
