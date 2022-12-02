package de.holhar.java_dev_kb.data_structures_and_algorithms.ch01_fundamental_data_structures.s1_arrays;

public class Scoreboard {

  private int numEntries = 0;
  private final GameEntry[] board;

  public Scoreboard(int capacity) {
    this.board = new GameEntry[capacity];
  }

  public void add(GameEntry e) {
    int newScore = e.getScore();

    if (numEntries < board.length || newScore > board[numEntries - 1].getScore()) {
      if (numEntries < board.length) {
        numEntries++;
      }

      int j = numEntries - 1;

      while (j > 0 && board[j - 1].getScore() < newScore) {
        board[j] = board[j - 1]; // shift entry from j-1 to j
        j--;
      }

      board[j] = e;
    }
  }

  public GameEntry remove(int i) throws IndexOutOfBoundsException {
    if (i < 0 || i >= numEntries) {
      throw new IndexOutOfBoundsException("Invalid index: " + i);
    }

    GameEntry temp = board[i];

    for (int j = i; i < numEntries; i++) {
      board[j] = board[j + 1]; // shift entry from j+1 to j
    }

    board[numEntries - 1] = null;

    numEntries--;

    return temp;
  }
}
