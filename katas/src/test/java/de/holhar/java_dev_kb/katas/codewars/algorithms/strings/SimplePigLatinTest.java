package de.holhar.java_dev_kb.katas.codewars.algorithms.strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SimplePigLatinTest {

  @Test
  void pigIt_1() {
    assertEquals("igPay atinlay siay oolcay", SimplePigLatin.pigIt("Pig latin is cool"));
  }

  @Test
  void pigIt_2() {
    assertEquals("hisTay siay ymay tringsay", SimplePigLatin.pigIt("This is my string"));
  }

  @Test
  void pigIt_3() {
    assertEquals("elloHay orldway !", SimplePigLatin.pigIt("Hello world !"));
  }
}
