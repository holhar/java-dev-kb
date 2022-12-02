package de.holhar.java_dev_kb.data_structures_and_algorithms.ch01_fundamental_data_structures.s1_arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Multi-dimensional array usage example.
 */
public class TicTacToe {

  private static final Logger logger = LoggerFactory.getLogger(TicTacToe.class);

  // players
  public static final int X = 1;
  public static final int O = -1;

  // empty cell
  public static final int EMPTY = 0;

  // game board
  private final int[][] board = new int[3][3];

  // current player
  private int player;

  public TicTacToe() {
    clearBoard();
  }

  // every cell should be empty
  public void clearBoard() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        board[i][j] = EMPTY;
      }
    }

    // the first player is 'X'
    player = X;
  }

  public void putMark(int i, int j) throws IllegalArgumentException {
    if ((i < 0) || (i > 2) || (j < 0) || (j > 2)) {
      throw new IllegalArgumentException("Invalid board position");
    }
    if (board[i][j] != EMPTY) {
      throw new IllegalArgumentException("Board position occupied");
    }

    // place the mark for the current player
    board[i][j] = player;

    // switch players (uses fact that O = - X)
    player = -player;
  }

  public boolean isWin(int mark) {
    return (
        (board[0][0] + board[0][1] + board[0][2] == mark * 3) // row 0
        || (board[1][0] + board[1][1] + board[1][2] == mark * 3) // row 1
        || (board[2][0] + board[2][1] + board[2][2] == mark * 3) // row 2
        || (board[0][0] + board[1][0] + board[2][0] == mark * 3) // column 0
        || (board[0][1] + board[1][1] + board[2][1] == mark * 3) // column 1
        || (board[0][2] + board[1][2] + board[2][2] == mark * 3) // column 2
        || (board[0][0] + board[1][1] + board[2][2] == mark * 3) // diagonal
        || (board[2][0] + board[1][1] + board[0][2] == mark * 3) // rev diag
    );
  }

  public int winner() {
    if (isWin(X)) {
      return (X);
    } else if (isWin(O)) {
      return (O);
    } else {
      return (0);
    }
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("\n");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        switch (board[i][j]) {
          case X:
            sb.append("X");
            break;
          case O:
            sb.append("O");
            break;
          case EMPTY:
            sb.append(" ");
            break;
          default:
            throw new IllegalStateException("This shouldn't happen");
        }

        // column boundary
        if (j < 2) {
          sb.append("|");
        }

      }

      // row boundary
      if (i < 2) {
        sb.append("\n-----\n");
      }
    }
    return sb.toString();
  }


  public static void main(String[] args) {

    TicTacToe game = new TicTacToe();

    game.putMark(1, 1);
    game.putMark(0, 2);

    game.putMark(2, 2);
    game.putMark(0, 0);

    game.putMark(0, 1);
    game.putMark(2, 1);

    game.putMark(1, 2);
    game.putMark(1, 0);

    game.putMark(2, 0);

    logger.info("{}", game);

    int winningPlayer = game.winner();

    String[] outcome = {"O wins", "Tie", "X wins"}; // rely on ordering
    int winnerIdx = 1 + winningPlayer;

    logger.info("{}", outcome[winnerIdx]);
  }
}
