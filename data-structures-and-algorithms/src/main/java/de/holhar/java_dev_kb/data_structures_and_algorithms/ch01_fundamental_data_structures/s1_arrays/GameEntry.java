package de.holhar.java_dev_kb.data_structures_and_algorithms.ch01_fundamental_data_structures.s1_arrays;

public class GameEntry {

  private final String name;
  private final int score;

  public GameEntry(String name, int score) {
    this.name = name;
    this.score = score;
  }

  public String getName() {
    return name;
  }

  public int getScore() {
    return score;
  }

  @Override
  public String toString() {
    return "GameEntry{" +
        "name='" + name + '\'' +
        ", score=" + score +
        '}';
  }
}
