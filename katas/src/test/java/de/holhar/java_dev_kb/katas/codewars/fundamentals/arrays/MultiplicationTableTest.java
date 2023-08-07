package de.holhar.java_dev_kb.katas.codewars.fundamentals.arrays;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class MultiplicationTableTest {

  @Test
  @DisplayName("n = 3")
  void test3() {
    int[][] test = {{1,2,3},{2,4,6},{3,6,9}};
    assertArrayEquals(test, MultiplicationTable.multiplicationTable(3));
  }
  @Test
  @DisplayName("n = 1")
  void test1(){
    int[][] test1 = {{1}};
    assertArrayEquals(test1, MultiplicationTable.multiplicationTable(1));
  }
}
