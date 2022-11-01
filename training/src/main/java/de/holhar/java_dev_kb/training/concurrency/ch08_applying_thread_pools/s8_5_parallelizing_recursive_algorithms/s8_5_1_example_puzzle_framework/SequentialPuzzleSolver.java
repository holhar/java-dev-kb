package de.holhar.java_dev_kb.training.concurrency.ch08_applying_thread_pools.s8_5_parallelizing_recursive_algorithms.s8_5_1_example_puzzle_framework;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Sequential puzzle solver.
 */
public class SequentialPuzzleSolver<P, M> {
  private final Puzzle<P, M> puzzle;
  private final Set<P> seen = new HashSet<>();

  public SequentialPuzzleSolver(Puzzle<P, M> puzzle) {
    this.puzzle = puzzle;
  }

  public List<M> solve() {
    P pos = puzzle.initialPosition();
    return search(new Node<P, M>(pos, null, null));
  }

  private List<M> search(Node<P,M> node) {
    if (!seen.contains(node.pos)) {
      seen.add(node.pos);

      if (puzzle.isGoal(node.pos)) {
        return node.asMoveList();
      }

      for (M move : puzzle.legalMoves(node.pos)) {
        P pos = puzzle.move(node.pos, move);
        Node<P, M> child = new Node<>(pos, move, node);
        List<M> result = search(child);
        if (result != null) {
          return result;
        }
      }
    }
    return null;
  }
}
