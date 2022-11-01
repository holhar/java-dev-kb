package de.holhar.java_dev_kb.training.concurrency.ch08_applying_thread_pools.s8_5_parallelizing_recursive_algorithms.s8_5_1_example_puzzle_framework;

import de.holhar.java_dev_kb.training.concurrency.utils.Immutable;
import java.util.LinkedList;
import java.util.List;

/**
 * Link node for the puzzle solver framework.
 */
@Immutable
public class Node<P, M> {

  final P pos;
  final M move;
  final Node<P, M> prev;

  public Node(P pos, M move, Node<P, M> prev) {
    this.pos = pos;
    this.move = move;
    this.prev = prev;
  }

  List<M> asMoveList() {
    List<M> solution = new LinkedList<>();
    for (Node<P, M> n = this; n.move != null; n = n.prev) {
      solution.add(0, n.move);
    }
    return solution;
  }
}
