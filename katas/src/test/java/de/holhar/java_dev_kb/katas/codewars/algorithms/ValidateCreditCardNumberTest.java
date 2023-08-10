package de.holhar.java_dev_kb.katas.codewars.algorithms;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ValidateCreditCardNumberTest {

  @Disabled
  @Test
  void test891(){
    assertFalse(ValidateCreditCardNumber.validate("891"));
  }

  @ParameterizedTest
  @MethodSource("doubleDigitsArguments")
  void doubleDigits(Integer[] givenNumbers, Integer[] expectedNumbers) {
    var actual = ValidateCreditCardNumber.doubleDigits(givenNumbers);
    assertArrayEquals(expectedNumbers, actual);
  }

  public static Stream<Arguments> doubleDigitsArguments() {
    return Stream.of(
        Arguments.of(
            new Integer[]{1, 7, 1, 4}, new Integer[]{2, 7, 2, 4},
            new Integer[]{1, 2, 3, 4, 5}, new Integer[]{1, 4, 3, 8, 5},
            new Integer[]{8, 9, 1}, new Integer[]{8, 18, 1}
        )
    );
  }
}
