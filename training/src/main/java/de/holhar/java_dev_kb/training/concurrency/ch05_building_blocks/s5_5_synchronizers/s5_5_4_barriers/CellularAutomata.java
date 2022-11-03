package de.holhar.java_dev_kb.training.concurrency.ch05_building_blocks.s5_5_synchronizers.s5_5_4_barriers;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Coordinating computation in cellular automation with CyclicBarrier.
 */
public class CellularAutomata {

    private final Board mainBoard;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CellularAutomata(Board board) {
        this.mainBoard = board;
        int count = Runtime.getRuntime().availableProcessors();
        this.barrier = new CyclicBarrier(count, mainBoard::commitNewValues);
        this.workers = new Worker[count];
        for (int i = 0; i < count; i++) {
            workers[i] = new Worker(mainBoard.getSubBoard(count, i));
        }
    }

    private class Worker implements Runnable {
        private final Board board;

        public Worker(Board board) {
            this.board = board;
        }

        public void run() {
            while (!board.hasConverged()) {
                for (int x = 0; x < board.getMaxX(); x++) {
                    for (int y = 0; y < board.getMaxY(); y++) {
                        board.setNewValue(x, y, computeNewValue(x, y));
                    }
                }
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException ex) {
                    return;
                }
            }
        }

        public void start() {
            for (int i = 0; i < workers.length; i++) {
                new Thread(workers[i]).start();
            }
            mainBoard.waitForConvergence();
        }

        private int computeNewValue(int x, int y) {
            // ...
            return x*y;
        }
    }
}

// Boilerplate code
class Board {

    private List<Board> subBoards;

    public Board getSubBoard(int count, int index) {
        return subBoards.get(index);
    }

    public void setNewValue(int x, int y, int newValue) {
        // ...
    }

    public void commitNewValues() {
        // ...
    }

    public boolean hasConverged() {
        // ..
        return false;
    }

    public void waitForConvergence() {
        // ...
    }

    public int getMaxX() {
        // ..
        return 1;
    }

    public int getMaxY() {
        // ..
        return 1;
    }
}
