package de.holhar.java_dev_kb.data_structures_and_algorithms.ch03_recursion.s1_illustrative_examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DrawRuler {

  private static final Logger logger = LoggerFactory.getLogger(DrawRuler.class);

  /**
   * Draws an English ruler for the given number of inches and major tick length
   */
  public static void drawRuler(int nInches, int majorLength) {
    drawLine(majorLength, 0);             // draw inch 0 line and label
    for (int j = 1; j < nInches; j++) {
      drawInterval(majorLength - 1);  // draw interior ticks for inch
      drawLine(majorLength, j);                   // draw inch j line and label
    }
  }

  private static void drawInterval(int centralLength) {
    if (centralLength >= 1) {
      drawInterval(centralLength - 1);  // recursively draw top interval
      drawLine(centralLength);                      // draw center tick line (without label)
      drawInterval(centralLength - 1);  // recursively draw bottom interval
    }
  }

  private static void drawLine(int tickLength, int tickLabel) {
    for (int j = 0; j < tickLength; j++) {
      logger.info("-");
    }

    if (tickLabel >= 0) {
      logger.info(" {}", tickLabel);
    }
    logger.info("\n");
  }

  /*
   * Draws a line with the given tick length (but no label).
   */
  private static void drawLine(int tickLength) {
    drawLine(tickLength, -1);
  }
}
