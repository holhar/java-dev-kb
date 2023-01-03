package de.holhar.java_dev_kb.katas.codewars.algorithms;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RangeExtractionTest {

  @Test
  void rangeExtraction1() {
    String expected = "-6,-3-1,3-5,7-11,14,15,17-20";
    String result = RangeExtraction.rangeExtraction(
        new int[]{-6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20}
    );
    assertEquals(expected, result);
  }

  @Test
  void rangeExtraction2() {
    String expected = "-3--1,2,10,15,16,18-20";
    String result = RangeExtraction.rangeExtraction(new int[] {-3,-2,-1,2,10,15,16,18,19,20});
    assertEquals(expected, result);
  }

  @Test
  void rangeExtraction3() {
    String expected = "-30,-27,-24,-22,-21,-18,-17,-14,-12,-10,-8,-5,-2,0,2,3,5,7,9,10,13,16,18,"
        + "19,21,22,25,27";
    String result = RangeExtraction.rangeExtraction(new int[] {-30,-27,-24,-22,-21,-18,-17,-14,-12,-10,-8,-5,-2,0,2,3,5,7,9,10,13,16,18,19,21,22,25,27});
    assertEquals(expected, result);
  }

}
