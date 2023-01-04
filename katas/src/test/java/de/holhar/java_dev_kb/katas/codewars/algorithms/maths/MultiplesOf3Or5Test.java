package de.holhar.java_dev_kb.katas.codewars.algorithms.maths;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MultiplesOf3Or5Test {

  @Test
  public void test() {
    assertEquals(23, new MultiplesOf3Or5().solution(10));
  }
}
