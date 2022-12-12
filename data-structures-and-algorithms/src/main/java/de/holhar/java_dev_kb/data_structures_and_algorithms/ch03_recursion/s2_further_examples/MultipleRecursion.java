package de.holhar.java_dev_kb.data_structures_and_algorithms.ch03_recursion.s2_further_examples;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generalizing from binary recursion, we define multiple recursion as a process in which a
 * method may make more than two recursive calls
 */
public class MultipleRecursion {

  private static final Logger logger = LoggerFactory.getLogger(MultipleRecursion.class);

  /**
   * Calculates the total dis usage (in bytes) of the portion of the file system rooted at the
   * given path, while printing a summary akin to the standard 'du' Unix tool.
   *
   * Runs in O(n) time.
   */
  public static long diskUsage(File root) {
    long total = root.length();
    if (root.isDirectory()) {
      for (String childName : root.list()) {
        File child = new File(root, childName);   // compose full path to child
        total += diskUsage(child);
      }
    }
    logger.info("{}\t{}", total, root);
    return total;
  }
}
