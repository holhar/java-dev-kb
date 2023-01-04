package de.holhar.java_dev_kb.katas.codewars.algorithms.strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AreWeAlternateTest {

  @Test
  public void isAlt1() {
    assertEquals(true, AreWeAlternate.isAlt("amazon"));
  }

  @Test
  public void isAlt2() {
    assertEquals(false, AreWeAlternate.isAlt("apple"));
  }

  @Test
  public void isAlt3() {
    assertEquals(true, AreWeAlternate.isAlt("banana"));
  }

  @Test
  public void isAlt4() {
    assertEquals(false, AreWeAlternate.isAlt("xtwtg"));
  }
}
