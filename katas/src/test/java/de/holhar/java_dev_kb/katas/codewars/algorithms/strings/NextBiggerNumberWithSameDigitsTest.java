package de.holhar.java_dev_kb.katas.codewars.algorithms.strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NextBiggerNumberWithSameDigitsTest {

  @Test
  public void nextBiggerNumber1() {
    assertEquals(21, NextBiggerNumberWithSameDigits.nextBiggerNumber(12));
  }

  @Test
  public void nextBiggerNumber2() {
    assertEquals(531, NextBiggerNumberWithSameDigits.nextBiggerNumber(513));
  }

  @Test
  public void nextBiggerNumber3() {
    assertEquals(2071, NextBiggerNumberWithSameDigits.nextBiggerNumber(2017));
  }

  @Test
  public void nextBiggerNumber4() {
    assertEquals(441, NextBiggerNumberWithSameDigits.nextBiggerNumber(414));
  }

  @Test
  public void nextBiggerNumber5() {
    assertEquals(414, NextBiggerNumberWithSameDigits.nextBiggerNumber(144));
  }

  @Test
  public void nextBiggerNumber6() {
    assertEquals(19009, NextBiggerNumberWithSameDigits.nextBiggerNumber(10990));
  }

  @Test
  public void nextBiggerNumber7() {
    assertEquals(-1, NextBiggerNumberWithSameDigits.nextBiggerNumber(9));
  }

  @Test
  public void nextBiggerNumber8() {
    assertEquals(-1, NextBiggerNumberWithSameDigits.nextBiggerNumber(111));
  }

  @Test
  public void nextBiggerNumber9() {
    assertEquals(-1, NextBiggerNumberWithSameDigits.nextBiggerNumber(531));
  }

  @Test
  public void nextBiggerNumber10() {
    assertEquals(1616268108, NextBiggerNumberWithSameDigits.nextBiggerNumber(1616268081));
  }

  @Test
  public void nextBiggerNumber11() {
    assertEquals(1234567908, NextBiggerNumberWithSameDigits.nextBiggerNumber(1234567890));
  }
}
