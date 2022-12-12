package de.holhar.java_dev_kb.data_structures_and_algorithms.ch03_recursion.s1_illustrative_examples;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiskUsage {

  private static final Logger logger = LoggerFactory.getLogger(DiskUsage.class);

  /**
   * Calculates the total dis usage (in bytes) of the portion of the file system rooted at the
   * given path, while printing a summary akin to the standard 'du' Unix tool
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
