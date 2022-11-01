package de.holhar.java_dev_kb.training.concurrency.ch08_applying_thread_pools.s8_5_parallelizing_recursive_algorithms.s8_5_1_example_puzzle_framework;

import java.util.Set;

/**
 * Abstraction for puzzles like the "sliding blocks puzzle".
 */
public interface Puzzle<P, M> {
  P initialPosition();
  boolean isGoal(P position);
  Set<M> legalMoves(P position);
  P move(P position, M move);
}
